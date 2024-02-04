package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository("csvStudent")
public class CsvStudents implements Students {

    public static final String FILE_NAME = "data/student.csv";
    private List<Student> studentList;

    private CsvStudents() {
    }
    private static class LazyHolder {
        public static final CsvStudents INSTANCE = new CsvStudents();
    }

    public static Students getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public void load() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {

            studentList = reader.lines()
                    .map(this::parsingScore)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            log.error("파일을 읽지 못하였습니다.", e);
        }
    }

    private Student parsingScore(String line) {

        String[] split = line.split(",");
        int seq = Integer.parseInt(split[0].trim());
        String name = split[1].trim();

        return new Student(seq, name);
    }

    @Override
    public Collection<Student> findAll() {
        return studentList;
    }

    @Override
    public void merge(Collection<Score> scores) {
        for(Student student : studentList){
            student.setScore(new Score(student.getSeq(),0));
        }
        for (Score score : scores) {
            int studentSeq = score.getStudentSeq();

            Optional<Student> optionalStudent = studentList.stream()
                    .filter(student -> student.getSeq() == studentSeq)
                    .findFirst();

            optionalStudent.ifPresent(student -> student.setScore(score));
        }
    }
}

package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentsTest {

    private List<Student> studentList;
    private Students students;

    @BeforeEach
    void setUp() {
        studentList = new ArrayList<>();
        studentList.add(new Student(1, "A"));
        studentList.add(new Student(2, "B"));
        studentList.add(new Student(3, "A"));
        studentList.add(new Student(4, "D"));

        students = CsvStudents.getInstance();
        students.load();
    }

    @Test
    void load() {
        Assertions.assertAll(
                () -> assertFalse(students.findAll().isEmpty())
        );
    }

    @Test
    void findAll() {
        Assertions.assertAll(
                () -> assertEquals(studentList, students.findAll()),
                () -> assertFalse(students.findAll().contains(new Student(9, "Z")))
        );
    }

    @Test
    void merge() {

        List<Score> scoreList = new ArrayList<>();
        scoreList.add(new Score(1, 30));
        scoreList.add(new Score(2, 80));
        scoreList.add(new Score(3, 70));

        students.merge(scoreList);

        Collection<Student> studentList = students.findAll();

        for (Score score : scoreList) {
            Optional<Student> student = studentList.stream()
                    .filter(s -> s.getSeq() == score.getStudentSeq())
                    .findFirst();

            Assertions.assertAll(
                    () -> assertTrue(student.isPresent()),
                    () -> assertEquals(score.getScore(), student.get().getScore().getScore())
            );
        }
    }
}
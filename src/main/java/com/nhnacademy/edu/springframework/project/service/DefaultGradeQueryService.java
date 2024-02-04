package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("defaultGradeQueryService")
public class DefaultGradeQueryService implements GradeQueryService {

    private final Scores scores;
    private final Students students;

    @Autowired
    public DefaultGradeQueryService(Scores scores, Students students) {
        this.scores = scores;
        this.students = students;
    }

    @Override
    public List<Score> getScoreByStudentName(String name) {
        List<Student> studentList = students.findAll()
                .stream()
                .filter(student -> student.getName().equals(name))
                .collect(Collectors.toList());

        List<Score> scoresList = new ArrayList<>();

        for(Student student : studentList){
            scoresList.add(student.getScore());
        }
        return scoresList;
    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        List<Score> scoreList = scores.findAll()
                .stream()
                .filter(score -> score.getStudentSeq() == seq)
                .collect(Collectors.toList());

        if(!scoreList.isEmpty()){
            return scoreList.get(0);
        } else {
            throw new NullPointerException();
        }
    }
}
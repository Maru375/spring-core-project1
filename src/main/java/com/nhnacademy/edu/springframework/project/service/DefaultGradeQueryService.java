package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultGradeQueryService implements GradeQueryService {

    @Override
    public List<Score> getScoreByStudentName(String name) {
        // 5: 학생 이름으로 점수를 반환합니다. 동명이인 고려합니다.
        // resources/data/student.csv 를 보면 학번, 이름으로 구성되어있습니다. 학번은 숫자, 이름은 알파벳입니다.
        // resources/data/score.csv 를 보면 학번, 점수로 구성되어있습니다. 학번은 숫자, 점수는 숫자입니다.
        //
        // 만약 getScoreByStudentName() 인자 name에 동명이인인 학생이름을 넣으면, 동명이인의 Score 들을 리턴합니다.
        // 인자 - 학생이름
        // 반환 - 점수리스트
        //
        // Hint. CsvStudents 클래스의 findAll() 이 있네요? 적절히 필터링하고 찾아오면 되겠죠?
        //
        Students students = CsvStudents.getInstance();
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
        // 6 : 학번으로 점수를 반환합니다. seq 인자가 학번입니다.
        Scores scores = CsvScores.getInstance();

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
package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Score;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GradeQueryServiceTest {

    private GradeQueryService service;

    @BeforeEach
    void setUp(){
        CsvDataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();
        service = new DefaultGradeQueryService();
    }

    @Test
    void getScoreByStudentName() {
        List<Score> score = service.getScoreByStudentName("A");

        Assertions.assertAll(
                () -> assertEquals(2, score.size()),
                () -> assertTrue(score.contains(new Score(1, 30))),
                () -> assertTrue(score.contains(new Score(3, 70)))
        );
    }

    @Test
    void getScoreByStudentSeq() {
        Score score1 = service.getScoreByStudentSeq(1);

        Assertions.assertAll(
                () -> assertEquals(30, score1.getScore()),
                () -> assertThrows(NullPointerException.class,
                        () -> service.getScoreByStudentSeq(999))
        );
    }
}
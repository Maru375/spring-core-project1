package com.nhnacademy.edu.springframework.project.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoresTest {

    private List<Score> scoreList;
    private Scores scores;

    @BeforeEach
    void setUp() {

        scoreList = new ArrayList<>();
        scoreList.add(new Score(1, 30));
        scoreList.add(new Score(2, 80));
        scoreList.add(new Score(3, 70));

        scores = CsvScores.getInstance();
        scores.load();
    }

    @Test
    void load() {
        Assertions.assertAll(
                () -> assertNotNull(scores.findAll())
        );
    }

    @Test
    void findAll() {
        Assertions.assertAll(
                () -> assertEquals(scoreList, scores.findAll()),
                () -> assertFalse(scores.findAll().contains(new Score(10,999)))
        );
    }
}
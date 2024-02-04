package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DataLoadServiceTest {

    @Test
    void loadAndMerge() {

        CsvDataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();

        Scores scores = CsvScores.getInstance();
        Students students = CsvStudents.getInstance();

        assertAll(
                () -> assertEquals(3, scores.findAll().size()),
                () -> assertEquals(4, students.findAll().size())
        );
    }
}
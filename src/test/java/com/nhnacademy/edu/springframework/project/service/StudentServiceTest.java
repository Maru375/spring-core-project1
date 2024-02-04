package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private DefaultStudentService studentService;

    @BeforeEach
    void setUp(){
        CsvDataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();
        studentService = new DefaultStudentService();
    }

    @Test
    void getPassedStudents() {
        Collection<Student> passedStudents = studentService.getPassedStudents();
        List<Student> studentList = new ArrayList<>(passedStudents);
        Assertions.assertAll(
                () -> assertNotNull(passedStudents),
                () -> assertEquals(2, studentList.size())
        );
    }

    @Test
    void getStudentsOrderByScore() {
        Collection<Student> orderByScore = studentService.getStudentsOrderByScore();
        List<Student> studentList = new ArrayList<>(orderByScore);
        Assertions.assertAll(
                () -> assertNotNull(orderByScore),
                () -> assertEquals(4, studentList.size())
        );
    }
}
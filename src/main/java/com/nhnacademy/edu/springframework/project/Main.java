package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.configuration.JavaConfig;
import com.nhnacademy.edu.springframework.project.service.CsvDataLoadService;
import com.nhnacademy.edu.springframework.project.service.DataLoadService;
import com.nhnacademy.edu.springframework.project.service.DefaultStudentService;
import com.nhnacademy.edu.springframework.project.service.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        DataLoadService dataLoadService = context.getBean("csvDataLoadService",CsvDataLoadService.class);
        dataLoadService.loadAndMerge();

        DefaultStudentService studentService = context.getBean("defaultStudentService",DefaultStudentService.class);
        Collection<Student> passedStudents = studentService.getPassedStudents();
        System.out.println(passedStudents);

        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
        System.out.println(orderedStudents);
    }
}

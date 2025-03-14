package com.advpro.profiling.tutorial.service;

import com.advpro.profiling.tutorial.model.Student;
import com.advpro.profiling.tutorial.model.StudentCourse;
import com.advpro.profiling.tutorial.repository.StudentCourseRepository;
import com.advpro.profiling.tutorial.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public List<StudentCourse> getAllStudentsWithCourses() {
        // Optimize by fetching all data in one query
        return studentCourseRepository.findAllWithStudents();
    }

    public Optional<Student> findStudentWithHighestGpa() {
        // Optimize by fetching the highest GPA directly from DB
        return studentRepository.findTopByOrderByGpaDesc();
    }

    public String joinStudentNames() {
        // Optimize by fetching only names from DB
        return studentRepository.findAllNames().stream()
                .collect(Collectors.joining(", "));
    }
}
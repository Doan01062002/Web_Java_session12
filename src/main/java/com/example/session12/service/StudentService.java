package com.example.session12.service;

import com.example.session12.model.StudentEntity;
import com.example.session12.repository.StudentRepository;

import java.util.List;

public class StudentService {
    private StudentRepository studentRepository = new StudentRepository();

    public List<StudentEntity> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public void addStudent(StudentEntity student) {
        studentRepository.addStudent(student);
    }

    public void updateStudent(StudentEntity student) {
        studentRepository.updateStudent(student);
    }

    public void deleteStudent(String studentId) {
        studentRepository.deleteStudent(studentId);
    }

    public List<StudentEntity> searchStudentsByName(String name) {
        return studentRepository.searchStudentsByName(name);
    }
}
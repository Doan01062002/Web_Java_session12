package com.example.session12.controller;

import com.example.session12.model.StudentEntity;
import com.example.session12.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentControllers {

    private StudentService studentService = new StudentService();

    @GetMapping
    public String listStudents(Model model) {
        List<StudentEntity> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "list_students";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new StudentEntity());
        return "add_student";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute("student") StudentEntity student) {
        studentService.addStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String studentId, Model model) {
        // Giả sử có phương thức getStudentById trong StudentService
        StudentEntity student = studentService.getAllStudents().stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
        model.addAttribute("student", student);
        return "edit_student";
    }

    @PostMapping("/edit")
    public String editStudent(@ModelAttribute("student") StudentEntity student) {
        studentService.updateStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") String studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/students";
    }

    @GetMapping("/search")
    public String searchStudents(@RequestParam("keyword") String keyword, Model model) {
        List<StudentEntity> students = studentService.searchStudentsByName(keyword);
        model.addAttribute("students", students);
        return "list_students";
    }
}

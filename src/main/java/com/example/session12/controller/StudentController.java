package com.example.session12.controller;

import com.example.session12.dao.StudentDAO;
import com.example.session12.model.Student;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/student")
public class StudentController extends HttpServlet {
    private StudentDAO dao = new StudentDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.getRequestDispatcher("/views/form.jsp").forward(req, res);
                break;
            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("student", dao.findById(id));
                req.getRequestDispatcher("/views/form.jsp").forward(req, res);
                break;
            case "delete":
                dao.delete(Integer.parseInt(req.getParameter("id")));
                res.sendRedirect("student");
                break;
            default:
                List<Student> students = dao.getAll();
                req.setAttribute("list", students);
                req.getRequestDispatcher("/views/list.jsp").forward(req, res);
                break;
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = req.getParameter("id").isEmpty() ? 0 : Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        Date dob = Date.valueOf(req.getParameter("dob"));

        Student s = new Student();
        s.setId(id);
        s.setName(name);
        s.setEmail(email);
        s.setDob(dob);

        if (id == 0) dao.insert(s);
        else dao.update(s);

        res.sendRedirect("student");
    }
}

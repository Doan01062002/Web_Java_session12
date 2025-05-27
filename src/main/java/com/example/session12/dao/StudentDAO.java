package com.example.session12.dao;



import com.example.session12.config.ConnectionDB;
import com.example.session12.model.Student;

import java.sql.*;
import java.util.*;

public class StudentDAO {

    public List<Student> getAll() {
        List<Student> list = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM student");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setEmail(rs.getString("email"));
                s.setDob(rs.getDate("dob"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(Student s) {
        try (Connection conn = ConnectionDB.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL insert_student(?,?,?)}");
            cs.setString(1, s.getName());
            cs.setString(2, s.getEmail());
            cs.setDate(3, s.getDob());
            cs.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Student s) {
        try (Connection conn = ConnectionDB.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL update_student(?,?,?,?)}");
            cs.setInt(1, s.getId());
            cs.setString(2, s.getName());
            cs.setString(3, s.getEmail());
            cs.setDate(4, s.getDob());
            cs.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = ConnectionDB.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL delete_student(?)}");
            cs.setInt(1, id);
            cs.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Student findById(int id) {
        Student s = null;
        try (Connection conn = ConnectionDB.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM student WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setEmail(rs.getString("email"));
                s.setDob(rs.getDate("dob"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}

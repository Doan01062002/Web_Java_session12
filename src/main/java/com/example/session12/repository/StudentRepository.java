package com.example.session12.repository;

import com.example.session12.config.ConnectionDB;
import com.example.session12.model.StudentEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    public List<StudentEntity> getAllStudents() {
        List<StudentEntity> students = new ArrayList<>();

        String query = "{CALL sp_getAllStudents()}";

        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement stmt = conn.prepareCall(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                StudentEntity student = new StudentEntity();
                student.setStudentId(rs.getString("id"));
                student.setFullName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setPhoneNumber(rs.getString("phone"));
                student.setGender(rs.getBoolean("sex"));
                student.setBirthDate(rs.getDate("bod"));
                student.setProfilePicture(rs.getString("avatar"));
                student.setAccountStatus(rs.getString("status"));
                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public void addStudent(StudentEntity student) {
        String query = "{CALL sp_insertStudent(?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setString(1, student.getStudentId());
            stmt.setString(2, student.getFullName());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getPhoneNumber());
            stmt.setBoolean(5, student.isGender());
            if (student.getBirthDate() != null) {
                stmt.setDate(6, new java.sql.Date(student.getBirthDate().getTime()));
            } else {
                stmt.setNull(6, Types.DATE);
            }
            stmt.setString(7, student.getProfilePicture());
            stmt.setString(8, student.getAccountStatus());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(StudentEntity student) {
        String query = "{CALL sp_updateStudent(?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setString(1, student.getStudentId());
            stmt.setString(2, student.getFullName());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getPhoneNumber());
            stmt.setBoolean(5, student.isGender());
            if (student.getBirthDate() != null) {
                stmt.setDate(6, new java.sql.Date(student.getBirthDate().getTime()));
            } else {
                stmt.setNull(6, Types.DATE);
            }
            stmt.setString(7, student.getProfilePicture());
            stmt.setString(8, student.getAccountStatus());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(String studentId) {
        String query = "{CALL sp_deleteStudent(?)}";

        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setString(1, studentId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<StudentEntity> searchStudentsByName(String name) {
        List<StudentEntity> students = new ArrayList<>();
        String query = "{CALL sp_searchStudent(?)}";

        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                StudentEntity student = new StudentEntity();
                student.setStudentId(rs.getString("id"));
                student.setFullName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setPhoneNumber(rs.getString("phone"));
                student.setGender(rs.getBoolean("sex"));
                student.setBirthDate(rs.getDate("bod"));
                student.setProfilePicture(rs.getString("avatar"));
                student.setAccountStatus(rs.getString("status"));
                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }
}

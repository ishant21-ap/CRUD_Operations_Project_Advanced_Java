package com.studentManagement.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.studentManagement.model.Student;

public class StudentDao {

    private String url = "jdbc:mysql://localhost:3306/Project";
    private String username = "root";
    private String password = "password";

    private static final String INSERT_STUDENT_SQL = "INSERT INTO studentManagement (name, email, dob) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_STUDENTS = "SELECT * FROM studentManagement";
    private static final String UPDATE_STUDENT_SQL = "UPDATE studentManagement SET name = ?, email = ?, dob = ? WHERE id = ?";
    private static final String DELETE_STUDENT_SQL = "DELETE FROM studentManagement WHERE id = ?";
    private static final String SELECT_STUDENT_BY_ID = "SELECT id, name, email, dob FROM studentManagement WHERE id = ?";

    public StudentDao() {
    }

    // Method to get a connection to the database
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    

    // Method to insert a new student
    public void insertStudent(Student student) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setDate(3, new Date(student.getDob().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to select all students
    public List<Student> selectAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date dob = rs.getDate("dob");
                students.add(new Student(id, name, email, dob));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Method to select a student by ID
    public Student selectStudentById(int id) {
        Student student = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date dob = rs.getDate("dob");
                student = new Student(id, name, email, dob);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    // Method to update a student
    public boolean updateStudent(Student student) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_SQL)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setDate(3, new Date(student.getDob().getTime()));
            statement.setInt(4, student.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    // Method to delete a student
    public boolean deleteStudent(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}

package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.database.ConnectionFactory;
import com.example.model.RegularStudent;
import com.example.model.Student;

public class RegularStudentDAO implements StudentDAO {
    private ConnectionFactory connectionFactory;

    public RegularStudentDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Student create(Student student) {
        if (student instanceof RegularStudent) {
            RegularStudent regularStudent = (RegularStudent) student;
            try (Connection connection = connectionFactory.createConnection()) {
                String sql = "INSERT INTO students (id, name, age, nrc, is_regular, created_at) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, regularStudent.getId());
                statement.setString(2, regularStudent.getName());
                statement.setInt(3, regularStudent.getAge());
                statement.setString(4, regularStudent.getNrc());
                statement.setBoolean(5, true);
                statement.setDate(6, new java.sql.Date(regularStudent.getCreatedAt().getTime()));
                statement.executeUpdate();
                return regularStudent;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public RegularStudent read(int id) {
        try (Connection connection = connectionFactory.createConnection()) {
            String sql = "SELECT * FROM students WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int studentId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String nrc = resultSet.getString("nrc");
                Boolean isRegular = resultSet.getBoolean("is_regular");
                Date createdAt = resultSet.getDate("created_at");
                
                return new RegularStudent(studentId, name, age, nrc, isRegular,  createdAt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Student student) {
        if (student instanceof RegularStudent) {
            RegularStudent regularStudent = (RegularStudent) student;
            try (Connection connection = connectionFactory.createConnection()) {
                String sql = "UPDATE students SET name = ?, age = ?, nrc = ? WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, regularStudent.getName());
                statement.setInt(2, regularStudent.getAge());
                statement.setString(3, regularStudent.getNrc());
                statement.setInt(4, regularStudent.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = connectionFactory.createConnection()) {
            String sql = "DELETE FROM students WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = connectionFactory.createConnection()) {
            String sql = "SELECT * FROM students";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	 int studentId = resultSet.getInt("id");
                 String name = resultSet.getString("name");
                 int age = resultSet.getInt("age");
                 String nrc = resultSet.getString("nrc");
                 Boolean isRegular = resultSet.getBoolean("is_regular");
                 Date createdAt = resultSet.getDate("created_at");
                 
                students.add(new RegularStudent(studentId, name, age, nrc, isRegular,  createdAt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}

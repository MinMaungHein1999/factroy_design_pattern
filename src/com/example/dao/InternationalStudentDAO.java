package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.database.ConnectionFactory;
import com.example.model.ImmigrationStatus;
import com.example.model.InternationalStudent;
import com.example.model.Student;
import com.example.model.VisaInformation;

public class InternationalStudentDAO implements StudentDAO {
    private ConnectionFactory connectionFactory;

    public InternationalStudentDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Student create(Student student) {
        if (student instanceof InternationalStudent) {
            InternationalStudent internationalStudent = (InternationalStudent) student;
            VisaInformationDAO visaInformationDAO = new VisaInformationDAO(connectionFactory);
            VisaInformation visaInformation = visaInformationDAO.create(internationalStudent.getVisaInformation());
            try (Connection connection = connectionFactory.createConnection()) {
                String sql = "INSERT INTO students (id, name, age, is_regular, nrc, immigration_status, visa_information_id, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, internationalStudent.getId());
                statement.setString(2, internationalStudent.getName());
                statement.setInt(3, internationalStudent.getAge());
                statement.setBoolean(4, internationalStudent.isRegular());
                statement.setString(5, visaInformation.getVisaNumber());
                statement.setInt(6, internationalStudent.getImmigrationStatus().getValue());
                statement.setObject(7, visaInformation != null ? visaInformation.getId() : null);
                statement.setDate(8, new java.sql.Date(internationalStudent.getCreatedAt().getTime()));
                statement.executeUpdate();
                return internationalStudent;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public InternationalStudent read(int id) {
        InternationalStudent internationalStudent = null;
        try (Connection connection = connectionFactory.createConnection()) {
            String sql = "SELECT * FROM students WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
           
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int studentId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                boolean isRegular = resultSet.getBoolean("is_regular");
                int immigrationStatus = resultSet.getInt("immigration_status");
                int visaInformationId = resultSet.getInt("visa_information_id");
                Date createdAt = resultSet.getDate("created_at");
                
                VisaInformationDAO visaInformationDAO = new VisaInformationDAO(connectionFactory);
                VisaInformation visaInformation = visaInformationDAO.read(visaInformationId);
               
                internationalStudent = new InternationalStudent(studentId, name, age, isRegular, createdAt, visaInformation, ImmigrationStatus.fromValue(immigrationStatus));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return internationalStudent;
    }

    @Override
    public void update(Student student) {
        if (student instanceof InternationalStudent) {
            InternationalStudent internationalStudent = (InternationalStudent) student;
            try (Connection connection = connectionFactory.createConnection()) {
                String sql = "UPDATE students SET name = ?, age = ?, is_regular = ?, immigration_status = ?, createdAt = ? WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, internationalStudent.getName());
                statement.setInt(2, internationalStudent.getAge());
                statement.setInt(4, internationalStudent.getId());
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
                boolean isRegular = resultSet.getBoolean("is_regular");
                int immigrationStatus = resultSet.getInt("immigration_status");
                Date createdAt = resultSet.getDate("created_at");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}

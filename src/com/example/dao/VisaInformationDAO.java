package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.example.database.ConnectionFactory;
import com.example.model.VisaInformation;
import com.example.model.VisaStatus;
import com.example.model.VisaType;

public class VisaInformationDAO {
    private ConnectionFactory connectionFactory;

    public VisaInformationDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public VisaInformation create(VisaInformation visaInformation) {
        try (Connection connection = connectionFactory.createConnection()) {
            String query = "INSERT INTO visa_informations (visa_type, visa_number, expiration_date, visa_status) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, visaInformation.getVisaType().getValue());
            statement.setString(2, visaInformation.getVisaNumber());
            statement.setDate(3, new Date(visaInformation.getExpirationDate().getTime()));
            statement.setInt(4, visaInformation.getVisaStatus().getValue());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    visaInformation.setId(generatedKeys.getInt(1));
                    return visaInformation;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public VisaInformation read(int id) {
        try (Connection connection = connectionFactory.createConnection()) {
            String query = "SELECT * FROM visa_informations WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                VisaInformation visaInformation = new VisaInformation();
                visaInformation.setId(resultSet.getInt("id"));
                visaInformation.setVisaType(VisaType.fromValue(resultSet.getInt("visa_type")));
                visaInformation.setVisaNumber(resultSet.getString("visa_number"));
                visaInformation.setExpirationDate(resultSet.getDate("expiration_date"));
                visaInformation.setVisaStatus(VisaStatus.fromValue(resultSet.getInt("visa_status")));
                
                return visaInformation;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(VisaInformation visaInformation) {
        try (Connection connection = connectionFactory.createConnection()) {
            String query = "UPDATE visa_informations SET visa_type = ?, visa_number = ?, expiration_date = ?, visa_status = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, visaInformation.getVisaType().getValue());
            statement.setString(2, visaInformation.getVisaNumber());
            statement.setDate(3, new Date(visaInformation.getExpirationDate().getTime()));
            statement.setInt(4, visaInformation.getVisaStatus().getValue());
            statement.setInt(5, visaInformation.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connection = connectionFactory.createConnection()) {
            String query = "DELETE FROM visa_informations WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<VisaInformation> getAll() {
        List<VisaInformation> visaInformations = new ArrayList<>();
        try (Connection connection = connectionFactory.createConnection()) {
            String query = "SELECT * FROM visa_informations";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                VisaInformation visaInformation = new VisaInformation();
                visaInformation.setId(resultSet.getInt("id"));
                visaInformation.setVisaType(VisaType.fromValue(resultSet.getInt("visa_type")));
                visaInformation.setVisaNumber(resultSet.getString("visa_number"));
                visaInformation.setExpirationDate(resultSet.getDate("expiration_date"));
                visaInformation.setVisaStatus(VisaStatus.fromValue(resultSet.getInt("visa_status")));
                visaInformations.add(visaInformation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visaInformations;
    }
}
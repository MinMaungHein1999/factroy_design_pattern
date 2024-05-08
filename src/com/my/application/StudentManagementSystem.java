package com.my.application;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.model.ImmigrationStatus;
import com.example.model.InternationalStudent;
import com.example.model.RegularStudent;
import com.example.model.Student;
import com.example.model.VisaInformation;
import com.example.model.VisaStatus;
import com.example.model.VisaType;
import com.example.service.student.create.*;

public class StudentManagementSystem {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int choice;

        do {
            System.out.println("Choose an option:");
            System.out.println("1. Register Regular Student");
            System.out.println("2. Register International Student");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                choice = -1;
            }

            switch (choice) {
                case 1:
                    registerRegularStudent(reader);
                    break;
                case 2:
                    registerInternationalStudent(reader);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 0.");
            }
        } while (choice != 0);

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void registerRegularStudent(BufferedReader reader) {
        System.out.println("Regular Student Registration:");
        int regularStudentId = getIntegerInput(reader, "Enter student ID: ");
        String regularStudentName = getStringInput(reader, "Enter student name: ");
        int regularStudentAge = getIntegerInput(reader, "Enter student age: ");
        String regularStudentNrc = getStringInput(reader, "Enter student NRC: ");

        StudentRegistration regularStudentRegistration = new RegularStudentRegistration();
        Student regularStudent = new RegularStudent(regularStudentId, regularStudentName, regularStudentAge, regularStudentNrc, true, new Date());
        regularStudentRegistration.registerStudent(regularStudent);
    }

    private static void registerInternationalStudent(BufferedReader reader) {
        System.out.println("International Student Registration:");
        
        int internationalStudentId = getIntegerInput(reader, "Enter student ID: ");
        String internationalStudentName = getStringInput(reader, "Enter student name: ");
        int internationalStudentAge = getIntegerInput(reader, "Enter student age: ");
        VisaType visaType = getVisaTypeInput(reader);
        String visaNumber = getStringInput(reader, "Enter visa number: ");
        Date expirationDate = getDateInput(reader, "Enter visa expiration date (YYYY-MM-DD): ");
        VisaStatus visaStatus = getVisaStatusInput(reader);
        ImmigrationStatus immigrationStatus = getImmigrationStatusInput(reader);

        VisaInformation visaInformation = new VisaInformation(visaType, visaNumber, expirationDate, visaStatus);
        Student internationalStudent = new InternationalStudent(internationalStudentId, internationalStudentName, internationalStudentAge, false, new Date(), visaInformation, immigrationStatus);

        // Now you can register the student
        StudentRegistration internationalStudentRegistration = new InternationalStudentRegistration();
        internationalStudentRegistration.registerStudent(internationalStudent);
    }

    private static int getIntegerInput(BufferedReader reader, String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer.");
            return getIntegerInput(reader, prompt);
        }
    }

    private static String getStringInput(BufferedReader reader, String prompt) {
        System.out.print(prompt);
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static VisaType getVisaTypeInput(BufferedReader reader) {
        System.out.println("Choose visa type:");
        for (VisaType type : VisaType.values()) {
            System.out.println((type.ordinal() + 1) + ". " + type.name());
        }
        int visaTypeOption = getIntegerInput(reader, "Enter option: ");
        while (visaTypeOption < 1 || visaTypeOption > VisaType.values().length) {
            System.out.println("Invalid option. Please select a valid visa type.");
            visaTypeOption = getIntegerInput(reader, "Enter option: ");
        }
        return VisaType.values()[visaTypeOption - 1];
    }

    private static Date getDateInput(BufferedReader reader, String prompt) {
        System.out.print(prompt);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        while (date == null) {
            try {
                date = dateFormat.parse(reader.readLine());
            } catch (IOException | ParseException e) {
                System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
            }
        }
        return date;
    }

    private static VisaStatus getVisaStatusInput(BufferedReader reader) {
        System.out.println("Choose visa status:");
        for (VisaStatus status : VisaStatus.values()) {
            System.out.println(status.getValue() + ". " + status.name());
        }
        int visaStatusOption = getIntegerInput(reader, "Enter option: ");
        while (visaStatusOption < 1 || visaStatusOption > VisaStatus.values().length) {
            System.out.println("Invalid option. Please select a valid visa status.");
            visaStatusOption = getIntegerInput(reader, "Enter option: ");
        }
        return VisaStatus.fromValue(visaStatusOption);
    }

    private static ImmigrationStatus getImmigrationStatusInput(BufferedReader reader) {
        System.out.println("Choose immigration status:");
        for (ImmigrationStatus status : ImmigrationStatus.values()) {
            System.out.println(status.getValue() + ". " + status.name());
        }
        int immigrationStatusOption = getIntegerInput(reader, "Enter option: ");
        while (immigrationStatusOption < 1 || immigrationStatusOption > ImmigrationStatus.values().length) {
            System.out.println("Invalid option. Please select a valid immigration status.");
            immigrationStatusOption = getIntegerInput(reader, "Enter option: ");
        }
        return ImmigrationStatus.fromValue(immigrationStatusOption);
    }
}

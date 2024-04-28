package com.my.application;

import com.example.lib.registration.*;

public class StudentManagementSystem {
	public static void main(String[] args) {
		 // Creating Regular Student Registration
        StudentRegistration regularStudentRegistration = new RegularStudentRegistration();
        regularStudentRegistration.registerStudent();

        // Creating International Student Registration
        StudentRegistration internationalStudentRegistration = new InternationalStudentRegistration();
        internationalStudentRegistration.registerStudent();
	}
}

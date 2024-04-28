package com.example.lib.registration;
import com.example.model.Student;
import com.example.model.RegularStudent;

public class RegularStudentRegistration extends StudentRegistration {
	
	@Override
	Student createStudent()
	{
		 Student student = new RegularStudent();
		 return student;
	}
}

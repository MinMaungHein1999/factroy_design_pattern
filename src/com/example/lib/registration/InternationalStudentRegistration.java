package com.example.lib.registration;

import com.example.model.InternationalStudent;
import com.example.model.Student;

public class InternationalStudentRegistration extends StudentRegistration{

	@Override
	public Student createStudent() {
		Student student = new InternationalStudent();
		return student;
	}
	
}

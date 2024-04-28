package com.example.lib.registration;

import com.example.model.Student;

public abstract class StudentRegistration {
	abstract Student createStudent();
	
	public void registerStudent() {
		
		Student student = createStudent();
		student.register();
		
	}
}

package com.example.service.student.create;

import com.example.database.ConnectionFactory;
import com.example.database.PgsqlConnectionFactory;
import com.example.model.Student;

public abstract class StudentRegistration {
	abstract Student createStudent(Student student);
	ConnectionFactory factory = new PgsqlConnectionFactory();
   
	public void registerStudent(Student student) {
		createStudent(student);
	}
}

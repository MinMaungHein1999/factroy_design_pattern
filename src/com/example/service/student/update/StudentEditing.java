package com.example.service.student.update;

import com.example.database.ConnectionFactory;
import com.example.database.PgsqlConnectionFactory;
import com.example.model.Student;

public abstract class StudentEditing {
	abstract Student updateStudent(int student_id, Student student);
	ConnectionFactory factory = new PgsqlConnectionFactory();
   
	public void editStudent(int student_id, Student student) {
		updateStudent(student_id, student);
	}
}

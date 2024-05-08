package com.example.service.student.create;

import com.example.dao.InternationalStudentDAO;
import com.example.dao.StudentDAO;
import com.example.model.Student;

public class InternationalStudentRegistration extends StudentRegistration{
	
	StudentDAO studentDAO = new InternationalStudentDAO(factory);

	@Override
	protected Student createStudent(Student student) {
		Student createStudent = studentDAO.create(student);
		return createStudent;
	}
	
}

package com.example.service.student.create;
import com.example.model.Student;
import com.example.dao.StudentDAO;
import com.example.dao.RegularStudentDAO;


public class RegularStudentRegistration extends StudentRegistration {
	
	StudentDAO studentDAO = new RegularStudentDAO(factory);

	 @Override
		protected Student createStudent(Student student) {
			Student createStudent = studentDAO.create(student);
			return createStudent;
		}

}

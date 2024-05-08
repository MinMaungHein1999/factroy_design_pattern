package com.example.dao;
import java.util.List;
import com.example.model.Student;

public interface StudentDAO {
	   Student create(Student student);
	    Student read(int id);
	    void update(Student student);
	    void delete(int id);
	    List<Student> getAll();

}

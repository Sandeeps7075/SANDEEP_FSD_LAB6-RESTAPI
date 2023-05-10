package com.sandeep.cf.services;

import java.util.List;

import com.sandeep.cf.model.Students;

public interface StudentServices {

	List<Students> getAllStudents();

	void saveStudent(Students Students);

	Students getStudentById(long id);

	void deleteStudentById(long id);

}

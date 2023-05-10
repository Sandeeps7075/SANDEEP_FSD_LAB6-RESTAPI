package com.sandeep.cf.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sandeep.cf.dao.StudentRepository;
import com.sandeep.cf.model.Students;

@Service
public class StudentServicesImpl implements StudentServices {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Students> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public void saveStudent(Students student) {
		this.studentRepository.save(student);
	}

	@Override
	public Students getStudentById(long id) {
		Optional<Students> optional = studentRepository.findById(id);
		Students student = null;
		if (optional.isPresent()) {
			student = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return student;
	}

	@Override
	public void deleteStudentById(long id) {
		this.studentRepository.deleteById(id);
	}

}

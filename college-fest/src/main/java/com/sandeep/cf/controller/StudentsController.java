package com.sandeep.cf.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sandeep.cf.model.Students;
import com.sandeep.cf.services.StudentServices;

@Controller
public class StudentsController {

	@Autowired
	private StudentServices studentServices;

	@GetMapping("/")
	public String liststudents(Model model) {
		model.addAttribute("listStudents", studentServices.getAllStudents());
		return "students";
	}

	@GetMapping("/showNewStudentForm")
	public String showNewStudentForm(Model model) {
		// create model attribute to bind form data
		Students student = new Students();
		model.addAttribute("student", student);
		return "create_student";
	}

	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") Students student) {
		// save student to database
		studentServices.saveStudent(student);
		return "redirect:/";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

		// get student from the service
		Students student = studentServices.getStudentById(id);

		// set student as a model attribute to pre-populate the form
		model.addAttribute("student", student);
		return "update_student";
	}

	@GetMapping("/deleteStudent/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id) {

		// call delete student method
		this.studentServices.deleteStudentById(id);
		return "redirect:/";
	}

	@GetMapping("/printAll")
	public String printAll(Model model) {
		model.addAttribute("listStudents", studentServices.getAllStudents());
		return "print_all";
	}

	@GetMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {
		ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}

}

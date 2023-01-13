package com.example.demo.student;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping(path = "api/v1/student")
public class StudentController { // this will have the resources for api

	private final StudentService service; // use component in student service class so it knows that it can acces it here


	@Autowired // this means the studentService above will be initialized for us and put into constuctor below
	public StudentController(StudentService studentService) {
		this.service = studentService; // won't work because we don't have instance of student service
	}

	@RequestMapping(path = "api/v1/student")
    @GetMapping("/")
	public List<Student> getStudents () {
		return service.getStudents(); // this is a better approach; this causes the student service class to be used
	}

	@PostMapping// post-mapping is used when you want to add new resources to a system
	public void registerNewStudent(@RequestBody Student student) { // here, we take the request body json details and map it into a student
		service.addNewStudent(student);
	}

	@DeleteMapping(path = "{studentId}") // here, we want to delete the student based upon student id
	public void deleteStudent(@PathVariable("studentId") Long studentId) { // path vairable is used to indicate deletion
		service.deleteStudent(studentId);
	}

	@PutMapping(path = "{studentId}")
	public void updateStudent(
			@PathVariable("studentId") Long studentId,
			@RequestParam(required = false) String name, // requestParam where both name and email (not required; can update one)
			@RequestParam(required = false) String email) {
		service.updateStudent(studentId, name, email);
	}
}

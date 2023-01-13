package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    //@Component
    public List<Student> getStudents () {
        return studentRepository.findAll(); // by implementing studentRepository. we have access through many methods through JPA
    }

    public void addNewStudent(Student student) { // this will add a new studnent to database
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) { // if the email is taken, than it throws an illegal exception
            throw new IllegalStateException("email invalid");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) { // method for deleting a student
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) { // student dosen't exists
            throw new IllegalStateException("student with id " + studentId + "does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional // becomes managed state
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId) //check if student exists with this id
                .orElseThrow(() -> new IllegalStateException("student w/id" + studentId + " does not exists")); // if no id, then an exception message will be shown

        if (name != null && // checks if name is valid and not the same as the current one
                name.length() > 0 && // if checks pass, then set the name
                !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && // same check as above for email method
                !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository
                    .findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email is currently being used");
            }
            student.setEmail(email);
        }
    }
}

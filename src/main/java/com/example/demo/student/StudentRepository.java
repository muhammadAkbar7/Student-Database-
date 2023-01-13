package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // this is the data access layer

public interface StudentRepository
        extends JpaRepository<Student, Long> { //type is student and id is type long

// SELECT * FROM student WHERE email = ?
        @Query("SELECT s FROM student s WHERE s.email = ?1")
        Optional<Student> findStudentByEmail(String email);
}

package com.server.hispath.student.domain.repository;

import java.util.Optional;

import com.server.hispath.student.domain.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByStudentNum(String studentNum);
    Optional<Student> findByEmail(String email);
}

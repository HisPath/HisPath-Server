package com.server.hispath.student.domain.repository;

import com.server.hispath.student.domain.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

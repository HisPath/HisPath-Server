package com.server.hispath.scholarship.domain.repository;

import com.server.hispath.scholarship.domain.Scholarship;
import com.server.hispath.student.domain.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {
    Scholarship findFirstByStudentAndSemester(Student student, String semester);
}


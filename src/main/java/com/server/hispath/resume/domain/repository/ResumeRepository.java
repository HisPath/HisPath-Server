package com.server.hispath.resume.domain.repository;

import java.util.List;

import com.server.hispath.resume.domain.Resume;
import com.server.hispath.student.domain.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByStudent(Student student);

    List<Resume> findTop6ByStudentOrderByUpdatedAtDesc(Student student);
}

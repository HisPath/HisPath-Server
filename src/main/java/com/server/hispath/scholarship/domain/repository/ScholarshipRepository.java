package com.server.hispath.scholarship.domain.repository;

import java.util.List;

import com.server.hispath.scholarship.domain.Scholarship;
import com.server.hispath.student.domain.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {
    Scholarship findFirstByStudentAndSemester(Student student, String semester);

    @Query("select s from Scholarship s " +
            "join fetch  s.student " +
            "join fetch  s.sDepartment " +
            "join fetch  s.sMajor1 " +
            "join fetch s.sMajor2 " +
            "where s.approved = :approved " +
            "and s.semester = :semester ")
    List<Scholarship> findAllByApprovedAndSemester(boolean approved, String semester);
}


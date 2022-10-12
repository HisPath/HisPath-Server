package com.server.hispath.student.domain.repository;

import java.util.Optional;

import com.server.hispath.student.domain.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByStudentNum(String studentNum);

    @Query("select a from Student a " +
            "left join fetch a.participants as p " +
            "left join fetch p.activity " +
            "where a.id = :id" )
    Optional<Student> findActivitiesByStudent(Long id);
    Optional<Student> findByEmail(String email);
}

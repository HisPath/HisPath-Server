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
            "where a.id = :id")
    Optional<Student> findActivitiesByStudent(Long id);

    Optional<Student> findByEmail(String email);

    @Query("select s from Student s " +
            "left join fetch s.participants as p " +
            "left join fetch p.activity as a " +
            "where s.id = :id")
    Optional<Student> findStudentWithActivities(Long id);

    @Query("select s from Student s " +
            "left join fetch  s.participants as p " +
            "left join fetch p.activity as a " +
            "left join fetch a.category " +
            "where s.id = :id " +
            "and a.semester = :semester")
    Optional<Student> findStudentWithIdAndSemester(Long id, String semester);

    @Query("select s from Student s " +
            "left join fetch s.participants as p " +
            "left join fetch p.activity as a " +
            "where s.id = :id and a.requestStatus = 1")
    Optional<Student> findStudentWithMActivities(Long id);

    @Query("select s from Student s " +
            "left join fetch s.scholarships " +
            "where s.id = :id ")
    Optional<Student> findStudentWithScholarships(Long id);
}

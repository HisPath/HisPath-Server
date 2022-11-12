package com.server.hispath.activity.domain.repository;

import java.util.List;
import java.util.Optional;

import com.server.hispath.activity.domain.Activity;
import com.server.hispath.student.domain.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllBySemester(String semester);
    // commit 테스트용
    @Query("select a from Activity a " +
            "left join fetch a.participants as p " +
            "left join fetch p.student " +
            "where a.id = :id ")
    Optional<Activity> findActivityWithStudents(Long id);

    @Query("select a from Activity a " +
            "left join fetch a.participants as p " +
            "left join fetch p.student " +
            "where a.id = :id " +
            "and a.personal = true ")
    Optional<Activity> findActivityWithStudentsAndPeronel(Long id);

    @Query("select a from Activity a " +
            "left join fetch a.participants as p " +
            "where p.student = :student " +
            "and a.semester = :semester")
    List<Activity> findActivitiesByStudentAndSemester(Student student, String semester);

    @Query("select distinct semester from Activity")
    List<String> bringSemester();

    List<Activity> findAllBySemesterAndRequestStatus(String semester, int requestStatus);

    List<Activity> findAllByRequestStatus(int requestStatus);

    @Query("select distinct a from Activity a " +
            "left join fetch a.participants as p " +
            "left join fetch p.student " +
            "where a.requestStatus = 1")
    List<Activity> findParticipatedActivity();
}

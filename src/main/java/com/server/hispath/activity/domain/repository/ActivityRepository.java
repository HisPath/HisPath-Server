package com.server.hispath.activity.domain.repository;

import java.util.List;
import java.util.Optional;

import com.server.hispath.activity.domain.Activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllBySemester(String semester);

    @Query("select a from Activity a " +
            "left join fetch a.participants as p " +
            "left join fetch p.student " +
            "where a.id = :id" )
    Optional<Activity> findActivityWithStudents(Long id);
}

package com.server.hispath.activity.domain.repository;

import java.util.List;

import com.server.hispath.activity.domain.Activity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllBySemester(String semester);
}

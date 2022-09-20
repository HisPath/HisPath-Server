package com.server.hispath.activity.domain.repository;

import com.server.hispath.activity.domain.Activity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}

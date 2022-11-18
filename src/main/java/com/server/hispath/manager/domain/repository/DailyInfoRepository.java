package com.server.hispath.manager.domain.repository;

import com.server.hispath.manager.domain.DailyInfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyInfoRepository extends JpaRepository<DailyInfo, Long> {
}

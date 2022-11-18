package com.server.hispath.manager.domain.repository;

import java.time.LocalDate;
import java.util.Optional;

import com.server.hispath.manager.domain.DailyInfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyInfoRepository extends JpaRepository<DailyInfo, Long> {
    Optional<DailyInfo> findFirstByDate(LocalDate date);
}

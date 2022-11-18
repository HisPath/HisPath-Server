package com.server.hispath.manager.domain.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.server.hispath.manager.domain.DailyInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DailyInfoRepository extends JpaRepository<DailyInfo, Long> {
    Optional<DailyInfo> findFirstByDate(LocalDate date);

    List<DailyInfo> findDailyInfoByDateBetweenOrderByDateAsc(LocalDate start, LocalDate end);

    @Query("select sum(d.loginCnt) from DailyInfo d ")
    Long getTotalLoginCnt();

}

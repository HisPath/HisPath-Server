package com.server.hispath.notice.domain.repository;

import com.server.hispath.activity.domain.Activity;
import com.server.hispath.notice.domain.Notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findTop6ByPubDateLessThanEqualAndExpDateGreaterThanEqualOrderByPubDateDesc(LocalDate pubDate, LocalDate expDate);

    @Query("select n from Notice n " +
            "where n.importance = true " +
            "and n.pubDate <= :today " +
            "and n.expDate >= :today")
    List<Notice> findAllImpInPub(LocalDate today);

}

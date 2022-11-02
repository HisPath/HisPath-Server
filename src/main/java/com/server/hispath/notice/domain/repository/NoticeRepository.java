package com.server.hispath.notice.domain.repository;

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

    List<Notice> findTop5ByPubDateLessThanEqualAndExpDateGreaterThanEqualOrderByPubDateDesc(LocalDate pubDate, LocalDate expDate);

    @Modifying
    @Query("update Notice n set n.viewCnt = n.viewCnt + 1 where n.id = :id")
    void increaseViewCntByOne(Long id);


}

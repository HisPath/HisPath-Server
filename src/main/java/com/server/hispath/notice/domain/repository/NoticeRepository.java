package com.server.hispath.notice.domain.repository;

import com.server.hispath.notice.domain.Notice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}

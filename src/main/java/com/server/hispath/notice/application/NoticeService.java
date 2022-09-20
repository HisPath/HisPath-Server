package com.server.hispath.notice.application;

import com.server.hispath.notice.domain.repository.NoticeRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

}

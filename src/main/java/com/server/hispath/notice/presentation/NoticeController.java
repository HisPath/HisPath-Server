package com.server.hispath.notice.presentation;

import com.server.hispath.notice.application.NoticeService;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;
}

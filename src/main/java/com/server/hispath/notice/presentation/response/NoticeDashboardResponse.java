package com.server.hispath.notice.presentation.response;

import java.time.LocalDateTime;

import com.server.hispath.notice.application.dto.NoticeDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDashboardResponse {
    private Long noticeId;
    private String title;
    private LocalDateTime pubDate;

}

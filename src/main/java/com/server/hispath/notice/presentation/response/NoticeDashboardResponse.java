package com.server.hispath.notice.presentation.response;

import java.time.LocalDate;

import com.server.hispath.notice.application.dto.DashboardNoticeDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDashboardResponse {
    private Long noticeId;
    private String title;
    private LocalDate pubDate;
    private LocalDate expDate;
    private LocalDate regDate;

    static public NoticeDashboardResponse of(DashboardNoticeDto dto) {
        return new NoticeDashboardResponse(dto.getId(), dto.getTitle(), dto.getPubDate(), dto.getExpDate(), dto.getRegDate());
    }
}

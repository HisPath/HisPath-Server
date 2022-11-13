package com.server.hispath.notice.application.dto;

import java.time.LocalDate;

import com.server.hispath.notice.domain.Notice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardNoticeDto {
    private Long id;
    private String title;
    private LocalDate pubDate;
    private LocalDate expDate;
    private LocalDate regDate;

    public static DashboardNoticeDto of(Notice notice) {
        return new DashboardNoticeDto(notice.getId(), notice.getTitle(), notice.getPubDate(), notice.getExpDate(), notice.getCreatedAt().toLocalDate());
    }
}

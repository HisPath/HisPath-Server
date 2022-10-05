package com.server.hispath.notice.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.hispath.manager.application.dto.ManagerDto;
import com.server.hispath.notice.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto{
    private Long id;
    private ManagerDto manager;
    private String title;
    private String content;
    private int viewCnt;
    private boolean importance;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate pubDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate expDate;

    public static NoticeDto from(Notice notice) {
        return new NoticeDto(notice.getId(), ManagerDto.from(notice.getManager()), notice.getTitle(),
                notice.getContent(), notice.getViewCnt(), notice.isImportance(), notice.getPubDate(), notice.getExpDate());
    }
}

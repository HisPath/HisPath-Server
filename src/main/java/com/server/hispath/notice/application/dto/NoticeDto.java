package com.server.hispath.notice.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.hispath.manager.application.dto.ManagerDto;
import com.server.hispath.notice.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDateTime regDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate pubDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate expDate;

    public static NoticeDto from(Notice notice) {
        return new NoticeDto(notice.getId(), ManagerDto.of(notice.getManager()), notice.getTitle(),
                notice.getContent(), notice.getViewCnt(), notice.isImportance(),notice.getCreatedAt() ,notice.getPubDate(), notice.getExpDate());
    }
}

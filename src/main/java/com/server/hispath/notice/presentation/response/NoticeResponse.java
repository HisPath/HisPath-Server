package com.server.hispath.notice.presentation.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.hispath.notice.application.dto.NoticeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeResponse {


    private Long id;
    private Long managerId;
    private String managerName;
    private String title;
    private String content;
    private int viewCnt;
    private boolean importance;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate pubDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate expDate;

    public static NoticeResponse from(NoticeDto dto) {
        return new NoticeResponse(dto.getId(), dto.getManager().getId(), dto.getManager().getName(),
                dto.getTitle(), dto.getContent(), dto.getViewCnt(), dto.isImportance(), dto.getPubDate(), dto.getExpDate());
    }
}

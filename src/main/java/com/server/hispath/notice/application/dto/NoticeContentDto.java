package com.server.hispath.notice.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.hispath.notice.presentation.request.NoticeRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeContentDto {
    private String title;
    private String content;
    private int viewCnt;
    private boolean importance;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate pubDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate expDate;

    public static NoticeContentDto from(NoticeRequest request){
        return new NoticeContentDto(request.getTitle(), request.getContent(),
                request.getViewCnt(), request.isImportance(), request.getPubDate(), request.getExpDate());
    }
}

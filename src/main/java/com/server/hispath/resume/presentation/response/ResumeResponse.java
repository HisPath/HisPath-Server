package com.server.hispath.resume.presentation.response;


import java.time.LocalDateTime;

import com.server.hispath.resume.application.dto.ResumeDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeResponse {
    private Long resumeId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public static ResumeResponse of(ResumeDto resumeDto) {
        return new ResumeResponse(resumeDto.getId(), resumeDto.getTitle(), resumeDto.getContent(),
                resumeDto.getCreatedAt(), resumeDto.getUpdateAt());
    }
}

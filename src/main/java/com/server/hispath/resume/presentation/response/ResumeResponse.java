package com.server.hispath.resume.presentation.response;


import com.server.hispath.resume.application.dto.ResumeDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeResponse {
    private Long resumeId;
    private String content;

    public static ResumeResponse of(ResumeDto resumeDto){
        return new ResumeResponse(resumeDto.getId(), resumeDto.getContent());
    }
}

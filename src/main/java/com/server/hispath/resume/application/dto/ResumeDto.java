package com.server.hispath.resume.application.dto;

import com.server.hispath.resume.domain.Resume;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDto {
    private Long id;
    private String content;

    public static ResumeDto of(Resume resume){
        return new ResumeDto(resume.getId(), resume.getContent());
    }
}

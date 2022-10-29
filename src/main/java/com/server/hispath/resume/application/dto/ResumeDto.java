package com.server.hispath.resume.application.dto;

import java.time.LocalDateTime;

import com.server.hispath.resume.domain.Resume;
import com.server.hispath.resume.presentation.request.ResumeCURequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public ResumeDto(Long id, ResumeCURequest request) {
        this.id = id;
        this.title = request.getTitle();
        this.content = request.getContent();
    }

    public ResumeDto(ResumeCURequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }

    public static ResumeDto of(Resume resume) {
        return new ResumeDto(resume.getId(), resume.getTitle(), resume.getContent(),
                resume.getCreatedAt(), resume.getUpdatedAt());
    }
}

package com.server.hispath.resume.application;

import com.server.hispath.exception.resume.ResumeNotFoundException;
import com.server.hispath.resume.application.dto.ResumeDto;
import com.server.hispath.resume.domain.Resume;
import com.server.hispath.resume.domain.repository.ResumeRepository;
import com.server.hispath.student.application.StudentService;
import com.server.hispath.student.domain.Student;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final StudentService studentService;

    public Long create(ResumeDto resumeDto) {
        Student student = studentService.findById(resumeDto.getId());
        Resume resume = Resume.builder()
                              .student(student)
                              .content(resumeDto.getContent())
                              .build();

        Resume savedResume = resumeRepository.save(resume);
        student.addResume(savedResume);
        return savedResume.getId();
    }

    public ResumeDto update(ResumeDto resumeDto) {
        Resume resume = this.findById(resumeDto.getId());
        resume.updateContent(resumeDto);
        return ResumeDto.of(resume);
    }

    public Resume findById(Long id) {
        return resumeRepository.findById(id).orElseThrow(ResumeNotFoundException::new);
    }
}

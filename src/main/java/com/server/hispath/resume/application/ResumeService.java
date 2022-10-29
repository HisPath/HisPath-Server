package com.server.hispath.resume.application;

import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.exception.resume.ResumeNotFoundException;
import com.server.hispath.resume.application.dto.ResumeDto;
import com.server.hispath.resume.domain.Resume;
import com.server.hispath.resume.domain.repository.ResumeRepository;
import com.server.hispath.student.application.StudentService;
import com.server.hispath.student.domain.Student;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final StudentService studentService;

    @Transactional
    public Long create(Long studentId, ResumeDto resumeDto) {
        Student student = studentService.findById(studentId);
        Resume resume = Resume.builder()
                              .student(student)
                              .title(resumeDto.getTitle())
                              .content(resumeDto.getContent())
                              .build();

        Resume savedResume = resumeRepository.save(resume);
        student.addResume(savedResume);
        return savedResume.getId();
    }

    @Transactional
    public ResumeDto update(ResumeDto resumeDto) {
        Resume resume = this.findById(resumeDto.getId());
        resume.updateContent(resumeDto);
        return ResumeDto.of(resume);
    }

    public Resume findById(Long id) {
        return resumeRepository.findById(id).orElseThrow(ResumeNotFoundException::new);
    }

    @Transactional
    public void delete(Long id) {
        resumeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ResumeDto find(Long id) {
        return ResumeDto.of(this.findById(id));
    }

    @Transactional(readOnly = true)
    public List<ResumeDto> findAllStudentResumes(Long studentId) {
        Student student = studentService.findById(studentId);
        return resumeRepository.findByStudent(student)
                               .stream()
                               .map(ResumeDto::of)
                               .collect(Collectors.toList());
    }
}

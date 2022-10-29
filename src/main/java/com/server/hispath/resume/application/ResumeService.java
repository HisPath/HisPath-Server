package com.server.hispath.resume.application;

import com.server.hispath.resume.domain.Resume;
import com.server.hispath.resume.domain.repository.ResumeRepository;
import com.server.hispath.student.application.StudentService;
import com.server.hispath.student.domain.Student;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private ResumeRepository resumeRepository;
    private StudentService studentService;

    public Long create(Long studentId, String content) {
        Student student = studentService.findById(studentId);
        Resume resume = Resume.builder()
                              .student(student)
                              .content(content)
                              .build();

        Resume savedResume = resumeRepository.save(resume);
        student.addResume(savedResume);
        return savedResume.getId();
    }
}

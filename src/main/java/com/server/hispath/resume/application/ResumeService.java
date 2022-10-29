package com.server.hispath.resume.application;

import com.server.hispath.resume.domain.repository.ResumeRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private ResumeRepository resumeRepository;
}

package com.server.hispath.resume.presentation;

import com.server.hispath.resume.application.ResumeService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ResumeController {
    private ResumeService resumeService;


}

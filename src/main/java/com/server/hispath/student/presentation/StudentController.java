package com.server.hispath.student.presentation;

import com.server.hispath.student.application.StudentService;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
}

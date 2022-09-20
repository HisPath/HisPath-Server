package com.server.hispath.student.application;

import com.server.hispath.student.domain.repository.StudentRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

}

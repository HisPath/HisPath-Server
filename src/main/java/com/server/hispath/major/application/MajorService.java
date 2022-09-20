package com.server.hispath.major.application;

import com.server.hispath.major.domain.repository.MajorRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MajorService {

    private final MajorRepository majorRepository;

}

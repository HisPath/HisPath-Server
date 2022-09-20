package com.server.hispath.major.presentation;

import com.server.hispath.major.application.MajorService;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MajorController {

    private final MajorService majorService;
}

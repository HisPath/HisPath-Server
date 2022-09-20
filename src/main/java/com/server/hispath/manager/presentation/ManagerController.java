package com.server.hispath.manager.presentation;

import com.server.hispath.manager.application.ManagerService;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;
}

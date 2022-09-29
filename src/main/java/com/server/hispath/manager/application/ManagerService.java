package com.server.hispath.manager.application;

import com.server.hispath.manager.domain.repository.ManagerRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

}

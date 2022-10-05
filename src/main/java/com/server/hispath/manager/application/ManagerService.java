package com.server.hispath.manager.application;

import com.server.hispath.category.domain.Category;
import com.server.hispath.exception.category.CategoryNotFoundException;
import com.server.hispath.exception.manager.ManagerNotFoundException;
import com.server.hispath.manager.domain.Manager;
import com.server.hispath.manager.domain.repository.ManagerRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    public Manager findById(Long id){
        return managerRepository.findById(id).orElseThrow(ManagerNotFoundException::new);
    }
}

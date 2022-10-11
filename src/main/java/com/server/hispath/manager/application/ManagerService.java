package com.server.hispath.manager.application;

import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.category.domain.Category;
import com.server.hispath.exception.category.CategoryNotFoundException;
import com.server.hispath.exception.manager.ManagerNotFoundException;
import com.server.hispath.manager.application.dto.ManagerCUDto;
import com.server.hispath.manager.application.dto.ManagerDto;
import com.server.hispath.manager.domain.Manager;
import com.server.hispath.manager.domain.repository.ManagerRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    public Long create(ManagerCUDto dto) {
        Manager savedManager = managerRepository.save(Manager.of(dto));
        return savedManager.getId();
    }

    public ManagerDto findManager(Long id) {
        return ManagerDto.of(this.findById(id));
    }

    public List<ManagerDto> findManagers() {
        return managerRepository.findAll()
                                .stream()
                                .map(ManagerDto::of)
                                .collect(Collectors.toList());
    }

    public ManagerDto update(Long id, ManagerCUDto dto){
        Manager manager = this.findById(id);
        manager.update(dto);
        return ManagerDto.of(manager);
    }

    public Long delete(Long id){
        managerRepository.deleteById(id);
        return id;
    }

    public Long approve(Long id){
        this.findById(id).approve();
        return id;
    }

    public Manager findById(Long id) {
        return managerRepository.findById(id).orElseThrow(ManagerNotFoundException::new);
    }
}

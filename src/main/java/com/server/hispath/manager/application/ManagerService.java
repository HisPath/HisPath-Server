package com.server.hispath.manager.application;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.exception.manager.ManagerNotFoundException;
import com.server.hispath.manager.application.dto.ManagerCUDto;
import com.server.hispath.manager.application.dto.ManagerDashboardDto;
import com.server.hispath.manager.application.dto.ManagerDto;
import com.server.hispath.manager.application.dto.ManagerUpdateDto;
import com.server.hispath.manager.domain.DailyInfo;
import com.server.hispath.manager.domain.Manager;
import com.server.hispath.manager.domain.repository.DailyInfoRepository;
import com.server.hispath.manager.domain.repository.ManagerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final DailyInfoRepository dailyInfoRepository;

    @Transactional
    public Long create(ManagerCUDto dto) {
        Manager savedManager = managerRepository.save(Manager.of(dto));
        return savedManager.getId();
    }

    @Transactional(readOnly = true)
    public ManagerDto findManager(Long id) {
        return ManagerDto.of(this.findById(id));
    }

    @Transactional(readOnly = true)
    public List<ManagerDto> findManagers() {
        return managerRepository.findAll()
                                .stream()
                                .map(ManagerDto::of)
                                .collect(Collectors.toList());
    }

    @Transactional
    public ManagerDto update(Long id, ManagerCUDto dto) {
        Manager manager = this.findById(id);
        manager.update(dto);
        return ManagerDto.of(manager);
    }

    @Transactional
    public ManagerDto update(Long id, ManagerUpdateDto dto){
        Manager manager = this.findById(id);
        manager.update(dto);
        return ManagerDto.of(manager);
    }

    @Transactional
    public Long delete(Long id) {
        managerRepository.deleteById(id);
        return id;
    }

    @Transactional
    public Long approve(Long managerId, int level) {
        Manager manager = this.findById(managerId);
        manager.approve();
        manager.updateLevel(level);
        return managerId;
    }

    public Manager findById(Long id) {
        return managerRepository.findById(id).orElseThrow(ManagerNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public ManagerDashboardDto getDashboard(Long managerId) {
        Manager manager = this.findById(managerId);
        List<DailyInfo> dailyInfos = dailyInfoRepository.findDailyInfoByDateBetweenOrderByDateAsc(LocalDate.now()
                                                                                                           .minusDays(6), LocalDate.now());
        Long[] loginCounts = dailyInfos.stream()
                                     .map(DailyInfo::getLoginCnt)
                                     .toArray(Long[]::new);
        Long totalLoginCnt = dailyInfoRepository.getTotalLoginCnt();

        return ManagerDashboardDto.of(manager, loginCounts, totalLoginCnt);
    }

    @Transactional(readOnly = true)
    public String getEmail(Long memberId){
        return this.findById(memberId).getEmail();
    }
}

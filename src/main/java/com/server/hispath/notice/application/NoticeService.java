package com.server.hispath.notice.application;

import com.server.hispath.exception.notice.NoticeNotFoundException;
import com.server.hispath.manager.application.ManagerService;
import com.server.hispath.manager.domain.Manager;
import com.server.hispath.notice.application.dto.DashboardNoticeDto;
import com.server.hispath.notice.application.dto.NoticeContentDto;
import com.server.hispath.notice.application.dto.NoticeDto;
import com.server.hispath.notice.domain.Notice;
import com.server.hispath.notice.domain.repository.NoticeRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    private final ManagerService managerService;

    private Notice findById(Long id) {
        return noticeRepository.findById(id).orElseThrow(NoticeNotFoundException::new);
    }

    @Transactional
    public Long create(Long managerId, NoticeContentDto dto) {
        Manager manager = managerService.findById(managerId);
        Notice notice = Notice.from(manager, dto);
        Notice savedNotice = noticeRepository.save(notice);
        return savedNotice.getId();
    }

    @Transactional(readOnly = true)
    public List<NoticeDto> findAll() {
        List<Notice> notices = noticeRepository.findAll();
        return notices.stream().map(NoticeDto::from).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public NoticeDto find(Long id) {
        Notice notice = this.findById(id);
        return NoticeDto.from(notice);
    }

    @Transactional
    public NoticeDto update(Long id, Long managerId, NoticeContentDto dto) {
        Notice notice = this.findById(id);
        Manager manager = managerService.findById(managerId);
        notice.update(manager, dto);

        return NoticeDto.from(notice);
    }

    @Transactional
    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<DashboardNoticeDto> findRecentNotice() {
        List<Notice> notices = noticeRepository.findTop6ByPubDateLessThanEqualAndExpDateGreaterThanEqualOrderByPubDateDesc(LocalDate.now(), LocalDate.now());

        return notices.stream()
                      .map(DashboardNoticeDto::of)
                      .collect(Collectors.toList());
    }
}

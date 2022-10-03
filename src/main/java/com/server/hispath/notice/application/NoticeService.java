package com.server.hispath.notice.application;

import com.server.hispath.exception.notice.NoticeNotFoundException;
import com.server.hispath.manager.application.ManagerService;
import com.server.hispath.manager.domain.Manager;
import com.server.hispath.notice.application.dto.NoticeContentDto;
import com.server.hispath.notice.application.dto.NoticeDto;
import com.server.hispath.notice.domain.Notice;
import com.server.hispath.notice.domain.repository.NoticeRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    private final ManagerService managerService;

    private Notice findById(Long id){
        return noticeRepository.findById(id).orElseThrow(NoticeNotFoundException::new);
    }


    //C

    @Transactional
    public Long create(Long managerId, NoticeContentDto dto) {
        Manager manager = managerService.findById(managerId);
        Notice notice = Notice.from(manager, dto);
        Notice savedNotice = noticeRepository.save(notice);
        return savedNotice.getId();
    }

    // R
    @Transactional
    public List<NoticeDto> findAll() {
        List<Notice> notices = noticeRepository.findAll();
        return notices.stream().map(NoticeDto::from).collect(Collectors.toList());
    }

    @Transactional
    public NoticeDto find(Long id){
        Notice notice = this.findById(id);
        return NoticeDto.from(notice);
    }



    // U

    @Transactional
    public NoticeDto update(Long id, Long managerId, NoticeContentDto dto){
        Notice notice = this.findById(id);
        Manager manager = managerService.findById(managerId);
        notice.update(manager, dto);

        return NoticeDto.from(notice);
    }


    // D
    @Transactional
    public void delete(Long id){
        noticeRepository.deleteById(id);
    }
}

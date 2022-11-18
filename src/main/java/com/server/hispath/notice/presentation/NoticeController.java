package com.server.hispath.notice.presentation;

import com.server.hispath.auth.domain.RequiredLogin;
import com.server.hispath.auth.domain.RequiredManagerLogin;
import com.server.hispath.docs.ApiDoc;
import com.server.hispath.notice.application.dto.DashboardNoticeDto;
import com.server.hispath.notice.application.dto.NoticeContentDto;
import com.server.hispath.notice.application.dto.NoticeDto;
import com.server.hispath.notice.application.NoticeService;

import com.server.hispath.notice.domain.Notice;
import com.server.hispath.notice.presentation.request.NoticeRequest;
import com.server.hispath.notice.presentation.response.NoticeDashboardResponse;
import com.server.hispath.notice.presentation.response.NoticeResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NoticeController {

    private final NoticeService noticeService;


    @PostMapping("/notice/add")
    @ApiOperation(value= ApiDoc.NOTICE_CREATE)
    @RequiredManagerLogin
    public ResponseEntity<Long> create(@RequestBody NoticeRequest request){
        Long id = noticeService.create(request.getManagerId(), NoticeContentDto.from(request));
        return ResponseEntity.ok(id);
    }

    @GetMapping("/notice")
    @RequiredLogin
    @ApiOperation(value = ApiDoc.NOTICE_READ_ALL)
    public ResponseEntity<List<NoticeResponse>> findAll() {
        List<NoticeResponse> responses = noticeService.findAll().stream().sorted(Comparator.comparing(NoticeDto::getRegDate).reversed()).map(NoticeResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/notice/imp")
    @ApiOperation(value = ApiDoc.NOTICE_READ_IMP)
    @RequiredLogin
    public ResponseEntity<List<NoticeDashboardResponse>> findImp() {
        List<NoticeDashboardResponse> responses = noticeService.findImp().stream().sorted(Comparator.comparing(DashboardNoticeDto::getPubDate).reversed()).map(NoticeDashboardResponse::of).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/notice/{id}")
    @ApiOperation(value = ApiDoc.NOTICE_READ)
    @RequiredLogin
    public ResponseEntity<NoticeResponse> find(@PathVariable Long id){
//        noticeService.increaseViewCnt(id);
        NoticeResponse response = NoticeResponse.from(noticeService.find(id));
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/notice/{id}")
    @ApiOperation(value = ApiDoc.NOTICE_UPDATE)
    @RequiredManagerLogin
    public ResponseEntity<NoticeResponse> update(@PathVariable Long id, @RequestBody NoticeRequest request){
        NoticeDto dto = noticeService.update(id, request.getManagerId(), NoticeContentDto.from(request));
        NoticeResponse response = NoticeResponse.from(dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/notice/{id}")
    @ApiOperation(value = ApiDoc.NOTICE_DELETE)
    @RequiredManagerLogin
    public ResponseEntity<Long> delete(@PathVariable Long id){
        noticeService.delete(id);
        return ResponseEntity.ok(id);
    }

}

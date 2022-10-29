package com.server.hispath.resume.presentation;

import com.server.hispath.docs.ApiDoc;
import com.server.hispath.resume.application.ResumeService;
import com.server.hispath.resume.presentation.request.ResumeCURequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ResumeController {
    private ResumeService resumeService;

    @PostMapping("/resume")
    @ApiOperation(value = ApiDoc.RESUME_CREATE)
    public ResponseEntity<Long> create(@RequestBody ResumeCURequest request){
        // Todo @Login 을 통해 API 를 호출 할 수 있도록 하기
        // Todo 현재는 그냥 단순 테스트를 위해 1번에 넣기
        Long response = resumeService.create(1L, request.getContent());
        return ResponseEntity.ok(response);
    }
    
}

package com.server.hispath.resume.presentation;

import com.server.hispath.docs.ApiDoc;
import com.server.hispath.resume.application.ResumeService;
import com.server.hispath.resume.application.dto.ResumeDto;
import com.server.hispath.resume.presentation.request.ResumeCURequest;
import com.server.hispath.resume.presentation.response.ResumeResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping("/resume")
    @ApiOperation(value = ApiDoc.RESUME_CREATE)
    public ResponseEntity<Long> create(@RequestBody ResumeCURequest request) {
        // Todo @Login 을 통해 API 를 호출 할 수 있도록 하기
        // Todo 현재는 그냥 단순 테스트를 위해 1번에 넣기
        Long response = resumeService.create(1L, new ResumeDto(request));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/resume/{id}")
    @ApiOperation(value = ApiDoc.RESUME_UPDATE)
    public ResponseEntity<ResumeResponse> update(@PathVariable Long id, @RequestBody ResumeCURequest request) {
        ResumeResponse response = ResumeResponse.of(resumeService.update(new ResumeDto(id, request)));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/resume/{id}")
    @ApiOperation(value = ApiDoc.RESUME_DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        resumeService.delete(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/resume/{id}")
    @ApiOperation(value = ApiDoc.RESUME_READ)
    public ResponseEntity<ResumeResponse> find(@PathVariable Long id) {
        return ResponseEntity.ok(ResumeResponse.of(resumeService.find(id)));
    }
}

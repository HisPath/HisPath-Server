package com.server.hispath.resume.presentation;

import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.activity.application.ActivityService;
import com.server.hispath.activity.application.dto.ActivityParticipantDto;
import com.server.hispath.docs.ApiDoc;
import com.server.hispath.resume.application.ResumeService;
import com.server.hispath.resume.application.dto.ResumeDto;
import com.server.hispath.resume.presentation.request.ResumeCURequest;
import com.server.hispath.resume.presentation.response.ResumeResponse;
import com.server.hispath.resume.presentation.response.ResumeStudentInfo;
import com.server.hispath.student.application.StudentService;
import com.server.hispath.student.application.dto.StudentDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ResumeController {
    private final ResumeService resumeService;
    private final StudentService studentService;
    private final ActivityService activityService;

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

    @GetMapping("/resume")
    @ApiOperation(value = ApiDoc.RESUME_READ)
    public ResponseEntity<ResumeResponse> find(@RequestParam Long resumeId) {
        return ResponseEntity.ok(ResumeResponse.of(resumeService.find(resumeId)));
    }

    @GetMapping("/resumes")
    @ApiOperation(value = ApiDoc.RESUME_READ_ALL)
    public ResponseEntity<List<ResumeResponse>> findALl() {
        // Todo @Login 을 통해 API 를 호출 할 수 있도록 하기
        // Todo 현재는 그냥 단순 테스트를 위해 1번에 넣기
        List<ResumeResponse> responses = resumeService.findAllStudentResumes(1L)
                                                      .stream()
                                                      .map(ResumeResponse::of)
                                                      .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/resume/info")
    @ApiOperation(value = ApiDoc.RESUME_INFO)
    public ResponseEntity<ResumeStudentInfo> findStudentActivityInfo() {
        // Todo @Login 을 통해 API 를 호출 할 수 있도록 하기
        // Todo 현재는 그냥 단순 테스트를 위해 1번에 넣기
        StudentDto studentDto = studentService.find(1L);
        List<ActivityParticipantDto> activities = activityService.findAllParticipantActivites(studentDto.getId(), "ALL", "ALL");
        return ResponseEntity.ok(new ResumeStudentInfo(studentDto, activities));
    }
}

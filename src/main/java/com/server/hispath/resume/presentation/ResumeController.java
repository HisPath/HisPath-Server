package com.server.hispath.resume.presentation;

import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.activity.application.ActivityService;
import com.server.hispath.activity.application.dto.ActivityParticipantDto;
import com.server.hispath.auth.domain.LoginStudent;
import com.server.hispath.auth.domain.RequiredLogin;
import com.server.hispath.auth.domain.StudentLogin;
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
    @RequiredLogin
    public ResponseEntity<Long> create(
            @StudentLogin LoginStudent loginStudent,
            @RequestBody ResumeCURequest request) {
        Long response = resumeService.create(loginStudent.getId(), new ResumeDto(request));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/resume/{id}")
    @ApiOperation(value = ApiDoc.RESUME_UPDATE)
    @RequiredLogin
    public ResponseEntity<ResumeResponse> update(@PathVariable Long id, @RequestBody ResumeCURequest request) {
        ResumeResponse response = ResumeResponse.of(resumeService.update(new ResumeDto(id, request)));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/resume/{id}")
    @ApiOperation(value = ApiDoc.RESUME_DELETE)
    @RequiredLogin
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        resumeService.delete(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/resume")
    @ApiOperation(value = ApiDoc.RESUME_READ)
    @RequiredLogin
    public ResponseEntity<ResumeResponse> find(@RequestParam Long resumeId) {
        return ResponseEntity.ok(ResumeResponse.of(resumeService.find(resumeId)));
    }

    @GetMapping("/resumes")
    @ApiOperation(value = ApiDoc.RESUME_READ_ALL)
    @RequiredLogin
    public ResponseEntity<List<ResumeResponse>> findALl(@StudentLogin LoginStudent loginStudent) {

        List<ResumeResponse> responses = resumeService.findAllStudentResumes(loginStudent.getId())
                                                      .stream()
                                                      .map(ResumeResponse::of)
                                                      .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/resume/info")
    @ApiOperation(value = ApiDoc.RESUME_INFO)
    @RequiredLogin
    public ResponseEntity<ResumeStudentInfo> findStudentActivityInfo(@StudentLogin LoginStudent loginStudent) {

        StudentDto studentDto = studentService.find(loginStudent.getId());
        List<ActivityParticipantDto> activities = activityService.findAllParticipantActivites(studentDto.getId(), "ALL", "ALL");
        return ResponseEntity.ok(new ResumeStudentInfo(studentDto, activities));
    }
}

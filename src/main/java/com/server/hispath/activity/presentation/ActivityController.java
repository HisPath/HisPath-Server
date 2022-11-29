package com.server.hispath.activity.presentation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.activity.application.ActivityService;
import com.server.hispath.activity.application.MActivityService;
import com.server.hispath.activity.application.dto.*;
import com.server.hispath.activity.presentation.request.ActivityApproveRequest;
import com.server.hispath.activity.presentation.request.ActivityCURequest;
import com.server.hispath.activity.presentation.request.StudentActivityCURequest;
import com.server.hispath.activity.presentation.response.ActivityParticipantResponse;
import com.server.hispath.activity.presentation.response.ActivityResponse;
import com.server.hispath.activity.presentation.response.SemesterResponse;
import com.server.hispath.auth.domain.LoginStudent;
import com.server.hispath.auth.domain.RequiredLogin;
import com.server.hispath.auth.domain.RequiredManagerLogin;
import com.server.hispath.auth.domain.StudentLogin;
import com.server.hispath.docs.ApiDoc;
import com.server.hispath.student.domain.Section;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ActivityController {
    private final ActivityService activityService;
    private final MActivityService mActivityService;

    @PostMapping("/activity")
    @ApiOperation(value = ApiDoc.ACTIVITY_CREATE)
    public ResponseEntity<Long> create(@RequestBody ActivityCURequest request) {
        Long id = activityService.create(request.getCategoryId(), ActivityContentDto.from(request));
        return ResponseEntity.ok(id);
    }

    @GetMapping("/activity/{id}")
    @ApiOperation(value = ApiDoc.ACTIVITY_READ)
    public ResponseEntity<ActivityResponse> find(@PathVariable Long id) {

        ActivityResponse response = ActivityResponse.from(activityService.find(id));
        return ResponseEntity.ok(response);

    }

    @GetMapping("/activities")
    @ApiOperation(value = ApiDoc.ACTIVITY_READ_ALL)
    public ResponseEntity<List<ActivityResponse>> findAll() {

        List<ActivityResponse> responses = activityService.findAll()
                                                          .stream()
                                                          .map(ActivityResponse::from)
                                                          .collect(Collectors.toList());
        return ResponseEntity.ok(responses);

    }

    @PutMapping("/activity/{id}")
    @ApiOperation(value = ApiDoc.ACTIVITY_UPDATE)
    public ResponseEntity<ActivityResponse> update(@PathVariable Long id, @RequestBody ActivityCURequest request) {
        ActivityDto dto = activityService.update(id, request.getCategoryId(), ActivityContentDto.from(request));
        ActivityResponse response = ActivityResponse.from(dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/activity/student/{id}")
    @ApiOperation(value = ApiDoc.ACTIVITY_DELETE)
    @RequiredLogin
    public ResponseEntity<Long> delete(@PathVariable Long id, @StudentLogin LoginStudent loginStudent) {
        activityService.deleteStudentActivity(loginStudent.getId(), id);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/semester")
    @ApiOperation(value = ApiDoc.ACTIVITY_READ_SEMESTER)
    public ResponseEntity<List<SemesterResponse>> findAllBySemester() {
        List<SemesterDto> semesterDtos = activityService.bringSemester();
        List<SemesterResponse> responses = semesterDtos.stream()
                                                       .map(SemesterResponse::from)
                                                       .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/sections")
    @ApiOperation(value = ApiDoc.SECTION_READ_ALL)
    @RequiredLogin
    public ResponseEntity<List<String>> getSections() {
        List<String> sections = Arrays.stream(Section.values())
                                      .map(Section::getName)
                                      .collect(Collectors.toList());
        return ResponseEntity.ok(sections);
    }

    @PostMapping("/student-activity")
    @ApiOperation(value = ApiDoc.STUDENT_ACTIVITY_CREATE)
    @RequiredLogin
    public ResponseEntity<Long> createStudentActivity(@StudentLogin LoginStudent loginStudent, @RequestBody StudentActivityCURequest request) {
        Long response = activityService.createStudentActivity(loginStudent.getId(), StudentActivityContentDto.of(request), ParticipantContentDto.of(request));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/student-activity/{id}")
    @ApiOperation(value = ApiDoc.STUDENT_ACTIVITY_UPDATE)
    @RequiredLogin
    public ResponseEntity<ActivityParticipantResponse> updateStudentActivity(@PathVariable Long id
            , @RequestBody StudentActivityCURequest request
            , @StudentLogin LoginStudent loginStudent
    ) {
        ActivityParticipantDto activityParticipantDto = activityService.updateStudentActivity(id, loginStudent.getId(), StudentActivityContentDto.of(request), ParticipantContentDto.of(request));
        return ResponseEntity.ok(ActivityParticipantResponse.of(activityParticipantDto));
    }

    @GetMapping("/student-activities")
    @ApiOperation(value = ApiDoc.STUDENT_ACTIVITY_READ_SEMESTER)
    @RequiredLogin
    public ResponseEntity<List<ActivityParticipantResponse>> findParticipatedActivities(
            @StudentLogin LoginStudent loginStudent,
            @RequestParam String semester,
            @RequestParam String section) {
        List<ActivityParticipantResponse> responses = activityService.findAllParticipantActivites(loginStudent.getId(), semester, section)
                                                                     .stream()
                                                                     .map(ActivityParticipantResponse::of)
                                                                     .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/studentactivity")
    @ApiOperation(value = ApiDoc.STUDENT_MILEAGE_READ)
    @RequiredLogin
    public ResponseEntity<MStudentActivityDetailDto> findActivtyByStudentId(@StudentLogin LoginStudent loginStudent) {
        return ResponseEntity.ok(mActivityService.findActivitiesByStudent(loginStudent.getId()));
    }

    @PutMapping("/activity/apply/{id}")
    @ApiOperation(value = ApiDoc.ACTIVITY_APPLY)
    @RequiredLogin
    public ResponseEntity<Void> applyActivity(@PathVariable Long id) {
        activityService.apply(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/activity/approve/{id}")
    @ApiOperation(value = ApiDoc.ACTIVITY_APPROVE)
    @RequiredManagerLogin
    public ResponseEntity<Void> approveActivity(@PathVariable Long id, @RequestBody ActivityApproveRequest request) {
        activityService.approve(id, request.getWeight());
        return ResponseEntity.ok(null);
    }

    @PutMapping("/activity/reject/{id}")
    @ApiOperation(value = ApiDoc.ACTIVITY_REJECT)
    @RequiredManagerLogin
    public ResponseEntity<Void> rejectActivity(@PathVariable Long id) {
        activityService.reject(id);
        return ResponseEntity.ok(null);
    }


    @GetMapping("/activity-detail/{activityId}")
    @ApiOperation(value = ApiDoc.ACTIVITY_STUDENT_DETAIL)
    @RequiredLogin
    public ResponseEntity<ActivityParticipantResponse> findParticipantActivityById(
            @PathVariable Long activityId,
            @StudentLogin LoginStudent loginStudent) {

        ActivityParticipantDto dto = activityService.findParticipantActivityById(loginStudent.getId(), activityId);
        return ResponseEntity.ok(ActivityParticipantResponse.of(dto));

    }
}

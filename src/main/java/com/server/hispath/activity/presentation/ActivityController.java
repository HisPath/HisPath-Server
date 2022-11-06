package com.server.hispath.activity.presentation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.activity.application.ActivityService;
import com.server.hispath.activity.application.dto.*;
import com.server.hispath.activity.application.MActivityService;
import com.server.hispath.activity.application.dto.ActivityContentDto;
import com.server.hispath.activity.application.dto.ActivityDto;
import com.server.hispath.activity.application.dto.MStudentActivityDetailDto;
import com.server.hispath.activity.application.dto.SemesterDto;
import com.server.hispath.activity.presentation.request.ActivityApproveRequest;
import com.server.hispath.activity.presentation.request.ActivityCURequest;
import com.server.hispath.activity.presentation.request.StudentActivityCURequest;
import com.server.hispath.activity.presentation.response.ActivityParticipantResponse;
import com.server.hispath.activity.presentation.response.ActivityResponse;
import com.server.hispath.activity.presentation.response.SemesterResponse;
import com.server.hispath.docs.ApiDoc;
import com.server.hispath.student.domain.Section;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.EvaluationName;

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

    @DeleteMapping("/activity/{id}")
    @ApiOperation(value = ApiDoc.ACTIVITY_DELETE)
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        activityService.delete(id);
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
    public ResponseEntity<List<String>> getSections() {
        List<String> sections = Arrays.stream(Section.values())
                                      .map(Section::getName)
                                      .collect(Collectors.toList());
        return ResponseEntity.ok(sections);
    }

    @PostMapping("/student-activity/{id}")
    @ApiOperation(value = ApiDoc.STUDENT_ACTIVITY_CREATE)
    public ResponseEntity<Long> createStudentActivity(@PathVariable Long id, @RequestBody StudentActivityCURequest request) {
        Long response = activityService.createStudentActivity(id, StudentActivityContentDto.of(request), ParticipantContentDto.of(request));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/student-activity/{id}")
    @ApiOperation(value = ApiDoc.STUDENT_ACTIVITY_UPDATE)
    public ResponseEntity<ActivityParticipantResponse> updateStudentActivity(@PathVariable Long id, @RequestBody StudentActivityCURequest request) {
        ActivityParticipantDto activityParticipantDto = activityService.updateStudentActivity(id, 1L, StudentActivityContentDto.of(request), ParticipantContentDto.of(request));
        return ResponseEntity.ok(ActivityParticipantResponse.of(activityParticipantDto));
    }

    @GetMapping("/student-activities/{id}")
    @ApiOperation(value = ApiDoc.STUDENT_ACTIVITY_READ_SEMESTER)
    public ResponseEntity<List<ActivityParticipantResponse>> findParticipatedActivities(@PathVariable Long id, @RequestParam String semester, @RequestParam String section) {
        // ToDo       Student ID 관련해서는 나중에 Login 처리하기
        List<ActivityParticipantResponse> responses = activityService.findAllParticipantActivites(id, semester, section)
                                                                     .stream()
                                                                     .map(ActivityParticipantResponse::of)
                                                                     .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/studentactivity/{id}")
    @ApiOperation(value = ApiDoc.STUDENT_MILEAGE_READ)
    public ResponseEntity<MStudentActivityDetailDto> findActivtyByStudentId(@PathVariable Long id) {
        return ResponseEntity.ok(mActivityService.findActivitiesByStudent(id));
    }

    @PutMapping("/activity/apply/{id}")
    @ApiOperation(value = ApiDoc.ACTIVITY_APPLY)
    public ResponseEntity<Void> applyActivity(@PathVariable Long id) {
        activityService.apply(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/activity/approve/{id}")
    @ApiOperation(value = ApiDoc.ACTIVITY_APPROVE)
    public ResponseEntity<Void> approveActivity(@PathVariable Long id, @RequestBody ActivityApproveRequest request) {
        activityService.approve(id, request.getWeight());
        return ResponseEntity.ok(null);
    }

    @PutMapping("/activity/reject/{id}")
    @ApiOperation(value = ApiDoc.ACTIVITY_REJECT)
    public ResponseEntity<Void> rejectActivity(@PathVariable Long id) {
        activityService.reject(id);
        return ResponseEntity.ok(null);
    }


    @GetMapping("/studentactivity/{id}")
    @ApiOperation(value = ApiDoc.STUDENT_MILEAGE_READ)
    public ResponseEntity<MStudentActivityDetailDto> findActivityByStudentId(@PathVariable Long id) {
        return ResponseEntity.ok(mActivityService.findActivitiesByStudent(id));
    }
    @GetMapping("/activity-detail/{activityId}")
    @ApiOperation(value = ApiDoc.ACTIVITY_STUDENT_DETAIL)
    public ResponseEntity<ActivityParticipantResponse> findParticipantActivityById(@PathVariable Long activityId) {
        // ToDo StudentId 는 나중에 로그인으로 바꿈
        return ResponseEntity.ok(ActivityParticipantResponse.of(activityService.findParticipantActivityById(1L, activityId)));

    }
}

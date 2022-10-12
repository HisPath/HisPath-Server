package com.server.hispath.activity.presentation;

import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.activity.application.ActivityService;
import com.server.hispath.activity.application.MActivityService;
import com.server.hispath.activity.application.dto.ActivityContentDto;
import com.server.hispath.activity.application.dto.ActivityDto;
import com.server.hispath.activity.application.dto.MStudentActivityDetailDto;
import com.server.hispath.activity.application.dto.SemesterDto;
import com.server.hispath.activity.presentation.request.ActivityCURequest;
import com.server.hispath.activity.presentation.response.ActivityResponse;
import com.server.hispath.activity.presentation.response.SemesterResponse;
import com.server.hispath.docs.ApiDoc;

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

    @PatchMapping("/activity/{id}")
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

    @GetMapping("/studentactivity/{id}")
    @ApiOperation(value = ApiDoc.STUDENT_MILEAGE_READ)
    public ResponseEntity<MStudentActivityDetailDto> findActivtyByStudentId(@PathVariable Long id) {
        return ResponseEntity.ok(mActivityService.findActivitiesByStudent(id));
    }
}

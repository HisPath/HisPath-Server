package com.server.hispath.activity.presentation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.server.hispath.activity.application.ActivityService;
import com.server.hispath.activity.application.MActivityService;
import com.server.hispath.activity.application.dto.*;
import com.server.hispath.activity.presentation.request.MActivityCURequest;
import com.server.hispath.activity.presentation.request.MParticipantRequest;
import com.server.hispath.activity.presentation.request.MStudentRegisterRequest;
import com.server.hispath.activity.presentation.response.*;
import com.server.hispath.docs.ApiDoc;
import com.server.hispath.student.application.StudentService;
import com.server.hispath.student.application.dto.StudentSimpleRefDto;
import com.server.hispath.util.ExcelManager;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MActivityController {

    private final MActivityService mActivityService;
    private final ActivityService activityService;
    private final StudentService studentService;

    @PostMapping("/mileage")
    @ApiOperation(value = ApiDoc.MILEAGE_CREATE)
    public ResponseEntity<Long> create(@RequestBody MActivityCURequest request) {
        Long id = mActivityService.create(MActivityContentDto.of(request));
        return ResponseEntity.ok(id);
    }

    @PostMapping("/mileages")
    @ApiOperation(value = ApiDoc.MILEAGES_CREATE)
    public ResponseEntity<Void> createMActivites(MultipartFile file) throws Exception {
        mActivityService.createAll(ExcelManager.getMActivities(ExcelManager.extract(file)));
        return ResponseEntity.ok(null);
    }

    @PutMapping("/mileage/{id}")
    @ApiOperation(value = ApiDoc.MILEAGE_UPDATE)
    public ResponseEntity<ActivityResponse> update(@PathVariable Long id, @RequestBody MActivityCURequest request) {
        ActivityDto dto = mActivityService.update(id, MActivityContentDto.of(request));
        ActivityResponse response = ActivityResponse.from(dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/mileage/{id}")
    @ApiOperation(value = ApiDoc.MILEAGE_DELETE)
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        activityService.delete(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/mileage/semester")
    @ApiOperation(value = ApiDoc.MILEAGE_READ_SEMESTER)
    public ResponseEntity<List<ActivityResponse>> findAllBySemester(@RequestParam String semester) {
        List<ActivityDto> activityDtos = mActivityService.findAllBySemester(semester);
        List<ActivityResponse> responses = activityDtos.stream()
                                                       .map(ActivityResponse::from)
                                                       .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/mileages")
    @ApiOperation(value = ApiDoc.MILEAGE_READ_ALL)
    public ResponseEntity<List<ActivityResponse>> findAll() {

        List<ActivityResponse> responses = mActivityService.findAll()
                                                           .stream()
                                                           .map(ActivityResponse::from)
                                                           .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/mileage/students")
    @ApiOperation(value = ApiDoc.MILEAGE_REGISTER_STUDENTS)
    public ResponseEntity<Void> registerStudents(@RequestPart(value = "file", required = false) MultipartFile file,
                                                 @RequestPart(value = "activityId") Long activityId) throws Exception {

        studentService.registerParticipants(activityId, ExcelManager.getStudentSimpleDatas(ExcelManager.extract(file)));

        return ResponseEntity.ok(null);
    }

    @PostMapping("/mileage/student")
    @ApiOperation(value = ApiDoc.MILEAGE_REGISTER_STUDENT)
    public ResponseEntity<Void> registerStudent(@RequestBody MStudentRegisterRequest request) {

        studentService.registerParticipant(request.getActivityId(), StudentSimpleRefDto.of(request));
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/mileage/student")
    @ApiOperation(value = ApiDoc.ACTIVITY_STUDENT_DELETE)
    public ResponseEntity<Void> deleteParticipant(@RequestBody MParticipantRequest request) {
        mActivityService.deleteParticipant(request.getStudentId(), request.getActivityId());
        return ResponseEntity.ok(null);
    }

    @GetMapping("/mileage/{id}")
    @ApiOperation(value = ApiDoc.MILEAGE_READ)
    public ResponseEntity<MActivityDetailDto> findMileageActivityById(@PathVariable Long id) {
        return ResponseEntity.ok(mActivityService.findDetailActivityInfo(id));
    }

    @GetMapping("/studentmileage/{id}")
    @ApiOperation(value = ApiDoc.STUDENT_MILEAGE_READ)
    public ResponseEntity<MStudentActivityDetailDto> findActivtyByStudentId(@PathVariable Long id) {
        return ResponseEntity.ok(mActivityService.findActivitiesByStudent(id));
    }

    @GetMapping("/semester/{id}")
    @ApiOperation(value = ApiDoc.STUDENT_READ_SEMESTER)
    public ResponseEntity<List<SemesterResponse>> findSemestersById(@PathVariable Long id) {
        List<SemesterDto> semesters = mActivityService.findSemestersById(id);
        List<SemesterResponse> responses = semesters.stream()
                .map(SemesterResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }



    @GetMapping("/student-mactivities/{id}")
    @ApiOperation(value = ApiDoc.STUDENT_ACTIVITY_READ_SEMESTER)
    public ResponseEntity<List<MActivityParticipantResponse>> findParticipatedActivities(@PathVariable Long id, @RequestParam String semester, @RequestParam String category) {
        List<MActivityParticipantResponse> responses = mActivityService.findAllParticipantActivities(id, semester, category)
                .stream()
                .map(MActivityParticipantResponse::of)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);


    }

    @GetMapping("/student-allmactivities/{id}")
    @ApiOperation(value = ApiDoc.STUDENT_ACTIVITY_READ_PARTICIPATE)
    public ResponseEntity<List<AllMActivityParticipantResponse>> findAllParticipatedActivities(@PathVariable Long id, @RequestParam String semester, @RequestParam String category) {

        List<AllMActivityParticipantResponse> responses;
        if(Objects.equals(category, "참여여부")){
            responses = mActivityService.findParticipatedActivities(id, semester, "ALL")
                    .stream()
                    .filter(example -> example.isParticipated())
                    .map(AllMActivityParticipantResponse::of)
                    .collect(Collectors.toList());
        }
        else {
            responses = mActivityService.findParticipatedActivities(id, semester, category)
                    .stream()
                    .map(AllMActivityParticipantResponse::of)
                    .collect(Collectors.toList());
        }
        return ResponseEntity.ok(responses);
    }

}
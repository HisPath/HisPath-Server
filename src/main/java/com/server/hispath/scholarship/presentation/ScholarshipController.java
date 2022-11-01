package com.server.hispath.scholarship.presentation;

import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.activity.application.ActivityService;

import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.docs.ApiDoc;
import com.server.hispath.scholarship.application.ScholarshipService;
import com.server.hispath.scholarship.application.dto.ScholarshipDto;
import com.server.hispath.scholarship.application.dto.SearchRequestDto;
import com.server.hispath.scholarship.presentation.request.ScholarshipCURequest;
import com.server.hispath.scholarship.presentation.response.ScholarshipActivityResponse;
import com.server.hispath.scholarship.presentation.response.ScholarshipDetailResponse;
import com.server.hispath.scholarship.presentation.response.ScholarshipResponse;
import com.server.hispath.util.ExcelManager;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScholarshipController {

    private final ScholarshipService scholarshipService;
    private final ActivityService activityService;

    @PostMapping("/scholarship")
    @ApiOperation(value = ApiDoc.SCHOLARSHIP_CREATE)
    public ResponseEntity<Long> create(@RequestBody ScholarshipCURequest request) {
        // Todo @Login 을 통해 API 를 호출 할 수 있도록 하기
        // Todo 현재는 그냥 단순 테스트를 위해 1번에 넣기
        Long response = scholarshipService.create(1L, request.getSemester());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/scholarships")
    @ApiOperation(value = ApiDoc.SCHOLARSHIP_READ_ALL)
    public ResponseEntity<List<ScholarshipResponse>> getScholarshipStudents(@RequestParam boolean approved, @RequestParam String semester) {
        List<ScholarshipResponse> responses = scholarshipService.findAllScholarshipStudent(approved, semester)
                                                                .stream()
                                                                .map(ScholarshipResponse::of)
                                                                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/scholarship/activities")
    @ApiOperation(value = ApiDoc.SCHOLARSHIP_ACTIVITIES)
    public ResponseEntity<ScholarshipDetailResponse> getScholarshipDetailInfo(@RequestParam Long studentId, @RequestParam String semester) {
        List<ScholarshipActivityResponse> scholarshipActivityResponses = activityService.findAllByStudentAndSemster(studentId, semester)
                                                                                        .stream()
                                                                                        .map(ScholarshipActivityResponse::of)
                                                                                        .collect(Collectors.toList());
        ScholarshipDto scholarshipDto = scholarshipService.findScholarshipStudent(studentId, semester);
        ScholarshipDetailResponse response = ScholarshipDetailResponse.from(scholarshipDto, scholarshipActivityResponses);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/scholarship/approval")
    @ApiOperation(value = ApiDoc.APPROVE_SCHOLARSHIPS)
    public ResponseEntity<Void> approveAll(@RequestPart(value = "file", required = false) MultipartFile file,
                                           @RequestPart(value = "semester") String semester) throws Exception {
        scholarshipService.approveAll(ExcelManager.getScholarshipApproveDatas(ExcelManager.extract(file)), semester);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/scholarship/students")
    @ApiOperation(value = ApiDoc.SCHOLARSHIP_SEARCH_STUDENT)
    public ResponseEntity<List<ScholarshipResponse>> searchScholarshipStudents(
            @RequestParam(required = false) String semester ,
            @RequestParam(required = false) String studentSemester,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String major1,
            @RequestParam(required = false) String major2
    ) {
        SearchRequestDto searchRequestDto = new SearchRequestDto(semester, studentSemester, department, major1, major2);
        List<ScholarshipResponse> responses = scholarshipService.searchScholarshipStudent(searchRequestDto)
                                                                .stream()
                                                                .map(ScholarshipResponse::of)
                                                                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}

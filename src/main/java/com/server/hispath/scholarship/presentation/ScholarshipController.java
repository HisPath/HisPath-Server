package com.server.hispath.scholarship.presentation;

import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.docs.ApiDoc;
import com.server.hispath.scholarship.application.ScholarshipService;
import com.server.hispath.scholarship.presentation.request.ScholarshipCURequest;
import com.server.hispath.scholarship.presentation.response.ScholarshipResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScholarshipController {

    private final ScholarshipService scholarshipService;

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
}

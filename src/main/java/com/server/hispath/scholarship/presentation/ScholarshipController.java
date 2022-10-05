package com.server.hispath.scholarship.presentation;

import com.server.hispath.docs.ApiDoc;
import com.server.hispath.scholarship.application.ScholarshipService;
import com.server.hispath.scholarship.application.dto.ScholarshipCUDto;
import com.server.hispath.scholarship.domain.Scholarship;
import com.server.hispath.scholarship.presentation.request.ScholarshipCURequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScholarshipController {

        private final ScholarshipService scholarshipService;
        // 장학금 신청
        @PostMapping("/scholarship")
        @ApiOperation(value = ApiDoc.SCHOLARSHIP_CREATE)
        public ResponseEntity<Long> apply(@RequestBody ScholarshipCURequest request) {
                Long appliedId = scholarshipService.apply(ScholarshipCUDto.of(request));
                return ResponseEntity.ok(appliedId);
        }

}

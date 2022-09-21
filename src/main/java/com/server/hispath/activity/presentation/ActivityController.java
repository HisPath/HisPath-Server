package com.server.hispath.activity.presentation;

import com.server.hispath.activity.application.ActivityService;
import com.server.hispath.activity.application.dto.ActivityContentDto;
import com.server.hispath.activity.presentation.request.ActivityCURequest;
import com.server.hispath.docs.ApiDoc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping("/activity")
    @ApiOperation(value = ApiDoc.ACTIVITY_CREATE)
    public ResponseEntity<Long> create(@RequestBody ActivityCURequest request) {
        Long id = activityService.create(request.getCategoryId(), ActivityContentDto.from(request));
        return ResponseEntity.ok(id);
    }
}

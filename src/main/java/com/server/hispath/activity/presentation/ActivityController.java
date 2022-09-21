package com.server.hispath.activity.presentation;

import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.activity.application.ActivityService;
import com.server.hispath.activity.application.dto.ActivityContentDto;
import com.server.hispath.activity.application.dto.ActivityDto;
import com.server.hispath.activity.presentation.request.ActivityCURequest;
import com.server.hispath.activity.presentation.response.ActivityResponse;
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
}

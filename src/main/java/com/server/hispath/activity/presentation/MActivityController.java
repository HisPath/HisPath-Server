package com.server.hispath.activity.presentation;

import com.server.hispath.activity.application.MActivityService;
import com.server.hispath.activity.application.dto.MActivityContentDto;
import com.server.hispath.activity.presentation.request.MActivityCURequest;
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
public class MActivityController {

    private final MActivityService mActivityService;

    @PostMapping("/mileage")
    @ApiOperation(value = ApiDoc.MILEAGE_CREATE)
    public ResponseEntity<Long> create(@RequestBody MActivityCURequest request) {
        Long id = mActivityService.create(request.getCategoryId(), MActivityContentDto.of(request));
        return ResponseEntity.ok(id);
    }
}

package com.server.hispath.activity.presentation;

import java.util.List;

import com.server.hispath.activity.application.MActivityService;
import com.server.hispath.activity.application.dto.MActivityContentDto;
import com.server.hispath.activity.presentation.request.MActivityCURequest;
import com.server.hispath.docs.ApiDoc;
import com.server.hispath.util.ExcelManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
        Long id = mActivityService.create(MActivityContentDto.of(request));
        return ResponseEntity.ok(id);
    }

    @PostMapping("/mileages")
    @ApiOperation(value = ApiDoc.MILEAGES_CREATE)
    public ResponseEntity<Void> createMActivites(MultipartFile file) throws Exception {
        mActivityService.createAll(ExcelManager.getMActivities(ExcelManager.extract(file)));
        return ResponseEntity.ok(null);
    }
}

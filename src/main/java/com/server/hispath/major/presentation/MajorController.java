package com.server.hispath.major.presentation;

import com.server.hispath.docs.ApiDoc;
import com.server.hispath.major.application.MajorService;

import com.server.hispath.major.application.dto.MajorContentDto;
import com.server.hispath.major.application.dto.MajorDto;
import com.server.hispath.major.presentation.request.MajorCURequest;
import com.server.hispath.major.presentation.response.MajorResponse;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MajorController {

    private final MajorService majorService;

    @PostMapping("/major")
    @ApiOperation(value = ApiDoc.MAJOR_CREATE)
    public ResponseEntity<Long> create(@RequestBody MajorCURequest request) {
         Long id = majorService.create(MajorContentDto.from(request));
         return ResponseEntity.ok(id);
    }


    @GetMapping("/major/{id}")
    @ApiOperation(value = ApiDoc.MAJOR_READ)
    public ResponseEntity<MajorResponse> find(@PathVariable Long id) {
        MajorResponse response = MajorResponse.from(majorService.find(id));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/major/{id}")
    @ApiOperation(value= ApiDoc.MAJOR_UPDATE)
    public ResponseEntity<MajorResponse> update(@PathVariable Long id, @RequestBody MajorCURequest request) {
        MajorDto dto = majorService.update(id, MajorContentDto.from(request));
        MajorResponse response = MajorResponse.from(dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/major/{id}")
    @ApiOperation(value = ApiDoc.MAJOR_DELETE)
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        majorService.delete(id);
        return ResponseEntity.ok(id);
    }


}

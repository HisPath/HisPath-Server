package com.server.hispath.major.presentation;

import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.auth.domain.RequiredLogin;
import com.server.hispath.auth.domain.RequiredManagerLogin;
import com.server.hispath.docs.ApiDoc;
import com.server.hispath.major.application.MajorService;
import com.server.hispath.major.application.dto.MajorContentDto;
import com.server.hispath.major.application.dto.MajorDto;
import com.server.hispath.major.presentation.request.MajorCURequest;
import com.server.hispath.major.presentation.response.MajorResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MajorController {

    private final MajorService majorService;

    @PostMapping("/major")
    @ApiOperation(value = ApiDoc.MAJOR_CREATE)
    @RequiredManagerLogin
    public ResponseEntity<Long> create(@RequestBody MajorCURequest request) {
        Long id = majorService.create(MajorContentDto.from(request));
        return ResponseEntity.ok(id);
    }


    @GetMapping("/major/{id}")
    @ApiOperation(value = ApiDoc.MAJOR_READ)
    @RequiredLogin
    public ResponseEntity<MajorResponse> find(@PathVariable Long id) {
        MajorResponse response = MajorResponse.from(majorService.find(id));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/major/{id}")
    @ApiOperation(value = ApiDoc.MAJOR_UPDATE)
    @RequiredManagerLogin
    public ResponseEntity<MajorResponse> update(@PathVariable Long id, @RequestBody MajorCURequest request) {
        MajorDto dto = majorService.update(id, MajorContentDto.from(request));
        MajorResponse response = MajorResponse.from(dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/major/{id}")
    @ApiOperation(value = ApiDoc.MAJOR_DELETE)
    @RequiredManagerLogin
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        majorService.delete(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/majors")
    @ApiOperation(value = ApiDoc.MAJOR_READ_ALL)
    @RequiredLogin
    public ResponseEntity<List<MajorResponse>> findAll() {
        List<MajorResponse> responses = majorService.findAll()
                                                    .stream()
                                                    .map(MajorResponse::from)
                                                    .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

}

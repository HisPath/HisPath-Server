package com.server.hispath.department.presentation;

import com.server.hispath.auth.domain.RequiredLogin;
import com.server.hispath.auth.domain.RequiredManagerLogin;
import com.server.hispath.department.application.DepartmentService;
import com.server.hispath.department.application.dto.DepartmentDto;
import com.server.hispath.department.presentation.request.DepartmentCRRequest;
import com.server.hispath.department.presentation.response.DepartmentResponse;
import com.server.hispath.docs.ApiDoc;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping("/department")
    @ApiOperation(value = ApiDoc.DEPARTMENT_CREATE)
    @RequiredManagerLogin
    public ResponseEntity<Long> create(@RequestBody DepartmentCRRequest request) {

        Long savedId = departmentService.create(DepartmentDto.of(request));
        return ResponseEntity.ok(savedId);
    }

    @GetMapping("/department/{id}")
    @ApiOperation(value = ApiDoc.DEPARTMENT_READ)
    @RequiredLogin
    public ResponseEntity<DepartmentResponse> find(@PathVariable Long id) {

        DepartmentDto dto = departmentService.find(id);
        DepartmentResponse response = DepartmentResponse.from(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/departments")
    @ApiOperation(value = ApiDoc.DEPARTMENT_READ_ALL)
    @RequiredLogin
    public ResponseEntity<List<DepartmentResponse>> findAll() {

        List<DepartmentDto> dtos = departmentService.findAll();
        List<DepartmentResponse> responses = dtos.stream()
                .map(DepartmentResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/department/{id}")
    @ApiOperation(value = ApiDoc.DEPARTMENT_UPDATE)
    @RequiredManagerLogin
    public ResponseEntity<DepartmentResponse> update(@PathVariable Long id, @RequestBody DepartmentCRRequest request) {
        DepartmentDto dto = departmentService.update(id, DepartmentDto.of(request));
        DepartmentResponse response = DepartmentResponse.from(dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/department/{id}")
    @ApiOperation(value = ApiDoc.DEPARTMENT_DELETE)
    @RequiredManagerLogin
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.ok(id);
    }
}

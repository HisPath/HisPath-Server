package com.server.hispath.manager.presentation;

import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.docs.ApiDoc;
import com.server.hispath.manager.application.ManagerService;
import com.server.hispath.manager.application.dto.ManagerCUDto;
import com.server.hispath.manager.presentation.request.ManagerApproveRequest;
import com.server.hispath.manager.presentation.request.ManagerCURequest;
import com.server.hispath.manager.presentation.response.ManagerResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping("/manager")
    @ApiOperation(value = ApiDoc.MANAGER_CREATE)
    public ResponseEntity<Long> create(@RequestBody ManagerCURequest request) {
        Long savedId = managerService.create(ManagerCUDto.of(request));
        return ResponseEntity.ok(savedId);
    }

    @GetMapping("/manager/{id}")
    @ApiOperation(value = ApiDoc.MANAGER_READ)
    public ResponseEntity<ManagerResponse> find(@PathVariable Long id) {
        ManagerResponse response = ManagerResponse.of(managerService.findManager(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/managers")
    @ApiOperation(value = ApiDoc.MANAGER_READ_ALL)
    public ResponseEntity<List<ManagerResponse>> findAll() {
        List<ManagerResponse> responses = managerService.findManagers()
                                                        .stream()
                                                        .map(ManagerResponse::of)
                                                        .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/manager/{id}")
    @ApiOperation(value = ApiDoc.MANAGER_UPDATE)
    public ResponseEntity<ManagerResponse> update(@PathVariable Long id, @RequestBody ManagerCURequest request) {
        ManagerResponse response = ManagerResponse.of(managerService.update(id, ManagerCUDto.of(request)));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/manager/approve/{id}")
    @ApiOperation(value = ApiDoc.MANAGER_APPROVE)
    public ResponseEntity<Long> approve(@RequestBody ManagerApproveRequest request) {
        Long response = managerService.approve(request.getManagerId(), request.getLevel());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/manager/{id}")
    @ApiOperation(value = ApiDoc.MANAGER_DELETE)
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        Long response = managerService.delete(id);
        return ResponseEntity.ok(response);
    }
}

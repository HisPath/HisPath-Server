package com.server.hispath.manager.presentation;

import com.server.hispath.manager.application.ManagerService;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ManagerController {

    private final ManagerService managerService;

//    @PostMapping("/")
//    @ApiOperation(value= ApiDoc.Manager_CREATE)
//    public ResponseEntity<Long> create(@RequestBody ManagerCURequest request){
//        Long savedId = managerService.create(ManagerCUDto.of(request));
//        return ResponseEntity.ok(savedId);
//    }
//
//    public ResponseEntity<ManagerResponse> find(@PathVariable Long id){
//        ManagerContentDto dto = managerService.find(id);
//        ManagerResponse response = ManagerResponse.from(dto);
//        return ResponseEntity.ok(response);
//    }




}

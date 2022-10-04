package com.server.hispath.student.presentation;

import com.server.hispath.docs.ApiDoc;
import com.server.hispath.student.application.StudentService;

import com.server.hispath.student.application.dto.StudentDto;
import com.server.hispath.student.presentation.request.StudentCURequest;
import com.server.hispath.student.presentation.response.StudentResponse;
import com.server.hispath.util.ExcelManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/student")
    @ApiOperation(value = ApiDoc.STUDENT_CREATE)
    public ResponseEntity<Long> create(@RequestBody StudentCURequest request) {
        Long savedId = studentService.create(StudentDto.of(request));
        return ResponseEntity.ok(savedId);
    }

    @PostMapping("/students")
    @ApiOperation(value = ApiDoc.STUDENTS_CREATE)
    public ResponseEntity<Void> createStudents(MultipartFile file) throws Exception {
        studentService.createAll(ExcelManager.getStudentDatas(ExcelManager.extract(file)));
        return ResponseEntity.ok(null);
    }

    @GetMapping("/student/{id}")
    @ApiOperation(value = ApiDoc.STUDENT_READ)
    public ResponseEntity<StudentResponse> find(@PathVariable Long id) {
        StudentDto dto = studentService.find(id);
        StudentResponse response = StudentResponse.from(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/students")
    @ApiOperation(value = ApiDoc.STUDENT_READ_ALL)
    public ResponseEntity<List<StudentResponse>> findAll() {
        List<StudentDto> dtos = studentService.findAll();
        List<StudentResponse> responses = dtos.stream()
                .map(StudentResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/student/{id}")
    @ApiOperation(value = ApiDoc.STUDENT_UPDATE)
    public ResponseEntity<StudentResponse> update(@PathVariable Long id, @RequestBody StudentCURequest request) {
        StudentDto dto = studentService.update(id, StudentDto.of(request));
        StudentResponse response = StudentResponse.from(dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/student/{id}")
    @ApiOperation(value = ApiDoc.STUDENT_DELETE)
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.ok(id);
    }
}

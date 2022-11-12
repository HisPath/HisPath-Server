package com.server.hispath.student.presentation;

import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.activity.application.ActivityService;
import com.server.hispath.activity.presentation.response.ActivityParticipantStatusResponse;
import com.server.hispath.docs.ApiDoc;
import com.server.hispath.notice.application.NoticeService;
import com.server.hispath.notice.application.dto.DashboardNoticeDto;
import com.server.hispath.student.application.StudentService;
import com.server.hispath.student.application.dto.StudentCUDto;
import com.server.hispath.student.application.dto.StudentDto;
import com.server.hispath.student.presentation.request.StudentCURequest;
import com.server.hispath.student.presentation.response.DashboardResponse;
import com.server.hispath.student.presentation.response.StudentResponse;
import com.server.hispath.util.ExcelManager;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;
    private final NoticeService noticeService;
    private final ActivityService activityService;

    @PostMapping("/student")
    @ApiOperation(value = ApiDoc.STUDENT_CREATE)
    public ResponseEntity<Long> create(@RequestBody StudentCURequest request) {
        Long savedId = studentService.create(StudentCUDto.of(request));
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

    @PutMapping("/student/{id}")
    @ApiOperation(value = ApiDoc.STUDENT_UPDATE)
    public ResponseEntity<StudentResponse> update(@PathVariable Long id, @RequestBody StudentCURequest request) {
        StudentDto dto = studentService.update(id, request.getDepartmentId(), request.getMajor1Id(), request.getMajor2Id(), StudentCUDto.of(request));
        StudentResponse response = StudentResponse.from(dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/student/{id}")
    @ApiOperation(value = ApiDoc.STUDENT_DELETE)
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/student/dashboard/{id}")
    @ApiOperation(value = ApiDoc.DASHBOARD)
    public ResponseEntity<DashboardResponse> getDashboardInfo(@PathVariable Long id) {
        StudentDto studentDto = studentService.find(id);
        List<DashboardNoticeDto> dashboardNoticeDtos = noticeService.findRecentNotice();
        return ResponseEntity.ok(DashboardResponse.from(studentDto, dashboardNoticeDtos));
    }

    @GetMapping("/student-activities/status")
    @ApiOperation(value = ApiDoc.STUDENT_ACTIVITY_READ_SEMESTER_SECTION_STATUS)
    public ResponseEntity<List<ActivityParticipantStatusResponse>> findStudentActivitiesWithStatus(@RequestParam String semester, @RequestParam String section) {
        // ToDo       Student ID 관련해서는 나중에 Login 처리하기 현재는 1L로 되어있음
        List<ActivityParticipantStatusResponse> responses = activityService.findAllPersonalParticipantActivites(1L, semester, section)
                                                                           .stream()
                                                                           .map(ActivityParticipantStatusResponse::of)
                                                                           .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}

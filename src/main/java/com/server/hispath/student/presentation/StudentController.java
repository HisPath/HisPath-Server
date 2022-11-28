package com.server.hispath.student.presentation;

import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.activity.application.ActivityService;
import com.server.hispath.activity.application.dto.ActivityParticipantDto;
import com.server.hispath.activity.presentation.response.ActivityParticipantStatusResponse;
import com.server.hispath.activity.presentation.response.SemesterResponse;
import com.server.hispath.auth.domain.LoginStudent;
import com.server.hispath.auth.domain.RequiredLogin;
import com.server.hispath.auth.domain.RequiredManagerLogin;
import com.server.hispath.auth.domain.StudentLogin;
import com.server.hispath.docs.ApiDoc;
import com.server.hispath.notice.application.NoticeService;
import com.server.hispath.notice.application.dto.DashboardNoticeDto;
import com.server.hispath.resume.application.ResumeService;
import com.server.hispath.resume.application.dto.ResumeDto;
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
    private final ResumeService resumeService;

    @PostMapping("/student")
    @ApiOperation(value = ApiDoc.STUDENT_CREATE)
    @RequiredManagerLogin
    public ResponseEntity<Long> create(@RequestBody StudentCURequest request) {
        Long savedId = studentService.create(StudentCUDto.of(request));
        return ResponseEntity.ok(savedId);
    }

    @PostMapping("/students")
    @ApiOperation(value = ApiDoc.STUDENTS_CREATE)
    @RequiredManagerLogin
    public ResponseEntity<Void> createStudents(@RequestPart("file") MultipartFile file) throws Exception {
        studentService.createAll(ExcelManager.getStudentDatas(ExcelManager.extract(file)));
        return ResponseEntity.ok(null);
    }

    @GetMapping("/student/{id}")
    @ApiOperation(value = ApiDoc.STUDENT_READ)
    @RequiredManagerLogin
    public ResponseEntity<StudentResponse> find(@PathVariable Long id) {
        StudentDto dto = studentService.find(id);
        StudentResponse response = StudentResponse.from(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/student")
    @ApiOperation(value = ApiDoc.STUDENT_READ)
    @RequiredLogin
    public ResponseEntity<StudentResponse> find(@StudentLogin LoginStudent loginStudent) {
        StudentDto dto = studentService.find(loginStudent.getId());
        StudentResponse response = StudentResponse.from(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/students")
    @ApiOperation(value = ApiDoc.STUDENT_READ_ALL)
    @RequiredManagerLogin
    public ResponseEntity<List<StudentResponse>> findAll() {
        List<StudentDto> dtos = studentService.findAll();
        List<StudentResponse> responses = dtos.stream()
                                              .map(StudentResponse::from)
                                              .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/student/{id}")
    @ApiOperation(value = ApiDoc.STUDENT_UPDATE)
    @RequiredManagerLogin
    public ResponseEntity<StudentResponse> update(@PathVariable Long id, @RequestBody StudentCURequest request) {
        StudentDto dto = studentService.update(id, request.getDepartmentId(), request.getMajor1Id(), request.getMajor2Id(), StudentCUDto.of(request));
        StudentResponse response = StudentResponse.from(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/student")
    @ApiOperation(value = ApiDoc.STUDENT_UPDATE)
    @RequiredLogin
    public ResponseEntity<StudentResponse> update(
            @StudentLogin LoginStudent loginStudent,
            @RequestBody StudentCURequest request) {
        StudentDto dto = studentService.update(loginStudent.getId(), request.getDepartmentId(),
                request.getMajor1Id(), request.getMajor2Id(), StudentCUDto.of(request));
        StudentResponse response = StudentResponse.from(dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/student/{id}")
    @ApiOperation(value = ApiDoc.STUDENT_DELETE)
    @RequiredManagerLogin
    public ResponseEntity<Long> delete(@PathVariable Long id) {

        studentService.delete(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/student/dashboard")
    @ApiOperation(value = ApiDoc.DASHBOARD)
    @RequiredLogin
    public ResponseEntity<DashboardResponse> getDashboardInfo(@StudentLogin LoginStudent loginStudent) {

        StudentDto studentDto = studentService.find(loginStudent.getId());
        List<DashboardNoticeDto> dashboardNoticeDtos = noticeService.findRecentNotice();
        List<ResumeDto> resumeDtos = resumeService.findRecentResumes(loginStudent.getId());
        List<ActivityParticipantDto> activityDtos = activityService.findRecentParticipantActivities(loginStudent.getId());

        return ResponseEntity.ok(DashboardResponse.from(studentDto, dashboardNoticeDtos, resumeDtos, activityDtos));
    }

    @GetMapping("/student-activities/status")
    @ApiOperation(value = ApiDoc.STUDENT_ACTIVITY_READ_SEMESTER_SECTION_STATUS)
    @RequiredLogin
    public ResponseEntity<List<ActivityParticipantStatusResponse>> findStudentActivitiesWithStatus(
            @StudentLogin LoginStudent loginStudent,
            @RequestParam String semester,
            @RequestParam String section) {

        List<ActivityParticipantStatusResponse> responses = activityService.findAllPersonalParticipantActivites(loginStudent.getId(), semester, section)
                                                                           .stream()
                                                                           .map(ActivityParticipantStatusResponse::of)
                                                                           .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/student/semesters")
    @ApiOperation(value = ApiDoc.STUDENT_SEMESTER)
    @RequiredLogin
    public ResponseEntity<List<SemesterResponse>> getStudentSemeters(
            @StudentLogin LoginStudent loginStudent
    ) {

        List<SemesterResponse> responses = studentService.getStudentSemesters(loginStudent.getId())
                                                         .stream()
                                                         .map(SemesterResponse::from)
                                                         .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}

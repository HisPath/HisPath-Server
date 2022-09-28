package com.server.hispath.common;

import com.server.hispath.student.domain.Student;
import com.server.hispath.student.domain.repository.StudentRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@
@RequestMapping("/test")
public class TestController {
    private final StudentRepository studentRepository;

    @GetMapping("/register/ref")
    public ResponseEntity<Void> testRegisterRefStudent() {
        for (int i = 0; i < 20; i++) {
            String studentNum = Integer.toString(i);
            String name = "학생" + i;
            Student student = Student.builder()
                                     .studentNum(studentNum)
                                     .name(name)
                                     .build();
            studentRepository.save(student);
        }
        return ResponseEntity.ok(null);
    }
}

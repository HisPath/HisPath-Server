package com.server.hispath.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.server.hispath.activity.application.ActivityService;
import com.server.hispath.activity.application.dto.ActivityContentDto;
import com.server.hispath.category.domain.Category;
import com.server.hispath.category.domain.repository.CategoryRepository;
import com.server.hispath.major.domain.repository.MajorRepository;
import com.server.hispath.notice.domain.repository.NoticeRepository;
import com.server.hispath.student.domain.Student;
import com.server.hispath.student.domain.repository.StudentRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final StudentRepository studentRepository;
    private final CategoryRepository categoryRepository;
    private final ActivityService activityService;
    private final NoticeRepository noticeRepository;
    private final MajorRepository majorRepository;

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

    @GetMapping("/init/category")
    public ResponseEntity<Void> testCategoryInit() {
        categoryRepository.save(Category.builder()
                                        .name("전공마일리지")
                                        .build());
        categoryRepository.save(Category.builder()
                                        .name("산학마일리지")
                                        .build());
        categoryRepository.save(Category.builder()
                                        .name("비교과-연구활동")
                                        .build());
        categoryRepository.save(Category.builder()
                                        .name("비교과-특강참여")
                                        .build());
        categoryRepository.save(Category.builder()
                                        .name("비교과-행사참여")
                                        .build());
        categoryRepository.save(Category.builder()
                                        .name("비교과-학회할동")
                                        .build());
        categoryRepository.save(Category.builder()
                                        .name("기타")
                                        .build());

        return ResponseEntity.ok(null);
    }
}

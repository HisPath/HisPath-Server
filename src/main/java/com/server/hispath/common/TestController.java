package com.server.hispath.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import com.server.hispath.activity.application.ActivityService;
import com.server.hispath.activity.application.dto.ActivityContentDto;
import com.server.hispath.category.domain.Category;
import com.server.hispath.category.domain.repository.CategoryRepository;
import com.server.hispath.major.domain.Major;
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

    @GetMapping("/register/ref")
    public ResponseEntity<Void> testRegisterRefStudent() {
        for (int i = 0; i < 10; i++) {
            String studentNum = Integer.toString(22200000+i);
            String name = "학생" + i;
            String email = "test" + i + "@handong.ac.kr";
            Random rand = new Random();
            int semester = rand.nextInt(10-1) + 1;
            Student student = Student.builder()
                                     .studentNum(studentNum)
                                     .name(name)
                                     .email(email)
                                     .semester(semester)
                                     .build();
            studentRepository.save(student);
        }
        return ResponseEntity.ok(null);
    }

//    @GetMapping("/init")
//    public ResponseEntity<Void> testInit() {
//        categoryRepository.save(Category.builder()
//                                        .name("특강")
//                                        .build());
//        categoryRepository.save(Category.builder()
//                                        .name("산학")
//                                        .build());
//        categoryRepository.save(Category.builder()
//                                        .name("대회")
//                                        .build());
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
////        activityService.create(1L, new ActivityContentDto("2022-2", false, 1,
////                "테스트특강", 3,
//        return ResponseEntity.ok(null);
//    }
}

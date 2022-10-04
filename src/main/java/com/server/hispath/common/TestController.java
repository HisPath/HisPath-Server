package com.server.hispath.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.server.hispath.activity.application.ActivityService;
import com.server.hispath.activity.application.dto.ActivityContentDto;
import com.server.hispath.category.domain.Category;
import com.server.hispath.category.domain.repository.CategoryRepository;
import com.server.hispath.exception.manager.ManagerNotFoundException;
import com.server.hispath.major.domain.repository.MajorRepository;
import com.server.hispath.manager.domain.Manager;
import com.server.hispath.manager.domain.repository.ManagerRepository;
import com.server.hispath.notice.domain.Notice;
import com.server.hispath.notice.domain.repository.NoticeRepository;
import com.server.hispath.student.domain.Student;
import com.server.hispath.student.domain.repository.StudentRepository;

import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@Builder
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final StudentRepository studentRepository;
    private final CategoryRepository categoryRepository;
    private final ActivityService activityService;
    private final NoticeRepository noticeRepository;
    private final MajorRepository majorRepository;
    private final ManagerRepository managerRepository;


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

    @GetMapping("/init/manager")
    public ResponseEntity<Void> initManager(){

        managerRepository.save(Manager.builder()
                .name("Adam")
                .email("Adams@handong.ac.kr")
                .department("CSEE")
                .approved(true).build());

        managerRepository.save(Manager.builder()
                .name("Boyci")
                .email("BCI@handong.ac.kr")
                .department("GLS")
                .approved(true).build());

        managerRepository.save(Manager.builder()
                .name("Charlie")
                .email("Chars@handong.ac.kr")
                .department("ME")
                .approved(true).build());

        managerRepository.save(Manager.builder()
                .name("David")
                .email("Dvd@handong.ac.kr")
                .department("CSEE")
                .approved(true).build());

        managerRepository.save(Manager.builder()
                .name("Ethen")
                .email("Eth@handong.ac.kr")
                .department("GLS")
                .approved(false).build());

        managerRepository.save(Manager.builder()
                .name("Jenny")
                .email("jenny@handong.ac.kr")
                .department("CUD")
                .approved(false).build());

        managerRepository.save(Manager.builder()
                .name("Lucy")
                .email("lucc@handong.ac.kr")
                .department("CSEE")
                .approved(true).build());

        return ResponseEntity.ok(null);
    }

    @GetMapping("/init/notice")
    public ResponseEntity<Void> initNotice(){
        List<Manager> l = new ArrayList<Manager>();
        for(Long i = Long.valueOf(1); i<8; i++){
            Manager temp = managerRepository.findById(i).orElseThrow(ManagerNotFoundException::new);
            l.add(temp);
        }

        noticeRepository.save(Notice.builder()
                .manager(l.get(1))
                .title("Welcome to notice")
                .content("This is the first notice")
                .viewCnt(100)
                .importance(true)
                .pubDate(LocalDate.of(2022, Month.SEPTEMBER, 5))
                .expDate(LocalDate.of(2022, Month.SEPTEMBER, 12))
                .build());

        noticeRepository.save(Notice.builder()
                .manager(l.get(2))
                .title("Second notice")
                .content("This is the second notice")
                .viewCnt(90)
                .importance(false)
                .pubDate(LocalDate.of(2022, Month.SEPTEMBER, 6))
                .expDate(LocalDate.of(2022, Month.SEPTEMBER, 18))
                .build());

        noticeRepository.save(Notice.builder()
                .manager(l.get(3))
                .title("SW festival")
                .content("This is the third notice")
                .viewCnt(50)
                .importance(false)
                .pubDate(LocalDate.of(2022, Month.SEPTEMBER, 9))
                .expDate(LocalDate.of(2022, Month.SEPTEMBER, 20))
                .build());

        noticeRepository.save(Notice.builder()
                .manager(l.get(4))
                .title("Capstone festival")
                .content("Capstone festival in November")
                .viewCnt(80)
                .importance(true)
                .pubDate(LocalDate.of(2022, Month.SEPTEMBER, 5))
                .expDate(LocalDate.of(2022, Month.SEPTEMBER, 30))
                .build());

        noticeRepository.save(Notice.builder()
                .manager(l.get(5))
                .title("GongPgi festival")
                .content("GongPgi festival in October")
                .viewCnt(100)
                .importance(true)
                .pubDate(LocalDate.of(2022, Month.SEPTEMBER, 20))
                .expDate(LocalDate.of(2022, Month.SEPTEMBER, 27))
                .build());

        noticeRepository.save(Notice.builder()
                .manager(l.get(2))
                .title("CSEE lecture")
                .content("At october 17 in NTH 313")
                .viewCnt(56)
                .importance(false)
                .pubDate(LocalDate.of(2022, Month.SEPTEMBER, 18))
                .expDate(LocalDate.of(2022, Month.SEPTEMBER, 25))
                .build());

        noticeRepository.save(Notice.builder()
                .manager(l.get(5))
                .title("SW news for October")
                .content("Is coming soon!")
                .viewCnt(1004)
                .importance(true)
                .pubDate(LocalDate.of(2022, Month.SEPTEMBER, 7))
                .expDate(LocalDate.of(2022, Month.OCTOBER, 12))
                .build());
        return ResponseEntity.ok(null);
    }


}
package com.server.hispath.common;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.server.hispath.activity.application.ActivityService;
import com.server.hispath.auth.domain.*;
import com.server.hispath.category.domain.Category;
import com.server.hispath.category.domain.repository.CategoryRepository;
import com.server.hispath.department.application.dto.DepartmentDto;
import com.server.hispath.department.domain.Department;
import com.server.hispath.department.domain.repository.DepartementRepository;
import com.server.hispath.exception.manager.ManagerNotFoundException;
import com.server.hispath.major.domain.repository.MajorRepository;
import com.server.hispath.manager.domain.Manager;
import com.server.hispath.manager.domain.repository.ManagerRepository;
import com.server.hispath.notice.domain.Notice;
import com.server.hispath.notice.domain.repository.NoticeRepository;
import com.server.hispath.student.domain.Student;
import com.server.hispath.student.domain.repository.StudentRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    private final DepartementRepository departementRepository;


//    @GetMapping("/register/ref")
//    public ResponseEntity<Void> testRegisterRefStudent() {
//        for (int i = 0; i < 10; i++) {
//            String studentNum = Integer.toString(22200000 + i);
//            String name = "학생" + i;
//            String email = "test" + i + "@handong.ac.kr";
//            int semester = 1;
//            Student student = Student.builder()
//                    .studentNum(studentNum)
//                    .name(name)
//                    .email(email)
//        }      .semester(semester)
//                .build();
//        studentRepository.save(student);
//        return ResponseEntity.ok(null);
//    }

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

    @GetMapping("/student/auth")
    @RequiredStudentLogin
    public ResponseEntity<Void> testStudentAuth(@StudentLogin LoginStudent loginStudent){
        System.out.println(loginStudent.getId());
        System.out.println(loginStudent.getStudentNum());
        return ResponseEntity.ok(null);
    }

    @GetMapping("/manager/auth")
    @RequiredManagerLogin
    public ResponseEntity<Void> testManagerAuth(@ManagerLogin LoginManager loginManager){
        System.out.println(loginManager.getId());
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
        noticeRepository.save(Notice.builder()
                .manager(l.get(1))
                .title("SW news for Novem")
                .content("Is going to be FUNNN")
                .viewCnt(0)
                .importance(true)
                .pubDate(LocalDate.of(2022, Month.OCTOBER, 21))
                .expDate(LocalDate.of(2022, Month.OCTOBER, 30))
                .build());
        noticeRepository.save(Notice.builder()
                .manager(l.get(6))
                .title("SW Festival ends")
                .content("See you next year!")
                .viewCnt(0)
                .importance(true)
                .pubDate(LocalDate.of(2022, Month.NOVEMBER, 2))
                .expDate(LocalDate.of(2022, Month.NOVEMBER, 9))
                .build());
        return ResponseEntity.ok(null);
    }

    @GetMapping("/init/student")
    public ResponseEntity<Void> initStudent(){
        studentRepository.save(Student.builder()
                .name("김한동")
//                .departmentId(1)
                .studentNum("22200000")
                .semester(5)
//                .major1("전산")
//                .major2("전자")
                .phone("010-1234-1234")
                .email("kim@handong.ac.kr")
                .profile("profile.url")
                .blog("blog.com")
                .githubId("@kim.github")
                .loginCnt(0L)
                .readme("kim's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("박한동")
//                .department("커뮤니케이션학부")
                .studentNum("22200001")
                .semester(3)
//                .major1("공연")
//                .major2("영상")
                .phone("010-1234-5678")
                .email("park@handong.ac.kr")
                .profile("profile.url")
                .blog("blog.com")
                .githubId("@park.github")
                .loginCnt(0L)
                .readme("park's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("이한동")
//                .department("국제어문학부")
                .studentNum("22200002")
                .semester(5)
//                .major1("국제관계학")
//                .major2("")
                .phone("010-5678-1234")
                .email("lee@handong.ac.kr")
                .profile("profile.url")
                .blog("blog.com")
                .githubId("@lee.github")
                .loginCnt(0L)
                .readme("lee's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("정한동")
//                .department("국제어문학부")
                .studentNum("22200003")
                .semester(6)
//                .major1("국제관계학")
//                .major2("")
                .phone("010-1111-1111")
                .email("jeong@handong.ac.kr")
                .profile("profile.url")
                .blog("blog.com")
                .githubId("@jeong.github")
                .loginCnt(0L)
                .readme("jeong's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("우한동")
//                .department("국제어문학부")
                .studentNum("22200004")
                .semester(6)
//                .major1("국제관계학")
//                .major2("")
                .phone("010-2222-3333")
                .email("woo@handong.ac.kr")
                .profile("profile.url")
                .blog("blog.com")
                .githubId("@woo.github")
                .loginCnt(0L)
                .readme("woo's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("위한동")
//                .department("국제어문학부")
                .studentNum("22200005")
                .semester(6)
//                .major1("국제관계학")
//                .major2("")
                .phone("010-4444-1234")
                .email("wi@handong.ac.kr")
                .profile("profile.url")
                .blog("blog.com")
                .githubId("@wi.github")
                .loginCnt(0L)
                .readme("wi's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("하한동")
//                .department("국제어문학부")
                .studentNum("22200006")
                .semester(8)
//                .major1("국제관계학")
//                .major2("")
                .phone("010-9999-1111")
                .email("ha@handong.ac.kr")
                .profile("profile.url")
                .blog("blog.com")
                .githubId("@ha.github")
                .loginCnt(0L)
                .readme("ha's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("마한동")
//                .department("국제어문학부")
                .studentNum("22200007")
                .semester(4)
//                .major1("국제관계학")
//                .major2("")
                .phone("010-4312-4312")
                .email("ma@handong.ac.kr")
                .profile("profile.url")
                .blog("blog.com")
                .githubId("@ma.github")
                .loginCnt(0L)
                .readme("ma's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("유한동")
//                .department("국제어문학부")
                .studentNum("22200008")
                .semester(6)
//                .major1("국제관계학")
//                .major2("")
                .phone("010-3434-2323")
                .email("yu@handong.ac.kr")
                .profile("profile.url")
                .blog("blog.com")
                .githubId("@yu.github")
                .loginCnt(0L)
                .readme("yu's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("진한동")
//                .department("국제어문학부")
                .studentNum("22200009")
                .semester(7)
//                .major1("국제관계학")
//                .major2("")
                .phone("010-8787-8787")
                .email("jin@handong.ac.kr")
                .profile("profile.url")
                .blog("blog.com")
                .githubId("@jin.github")
                .loginCnt(0L)
                .readme("jin's readme")
                .build());
        return ResponseEntity.ok(null);
    }

    @GetMapping("/init/department")
    public ResponseEntity<Void> initDepartment(){
        departementRepository.save(Department.builder()
                .name("전산전자")
                .build());
        departementRepository.save(Department.builder()
                .name("국제어문")
                .build());
        departementRepository.save(Department.builder()
                .name("경영경제")
                .build());
        departementRepository.save(Department.builder()
                .name("법학부")
                .build());
        departementRepository.save(Department.builder()
                .name("커뮤니케이션")
                .build());
        departementRepository.save(Department.builder()
                .name("상당복지")
                .build());
        departementRepository.save(Department.builder()
                .name("상당복지")
                .build());
        departementRepository.save(Department.builder()
                .name("공간환경시스템")
                .build());
        departementRepository.save(Department.builder()
                .name("콘텐츠융합디자인")
                .build());
        departementRepository.save(Department.builder()
                .name("기계제어")
                .build());
        departementRepository.save(Department.builder()
                .name("ICT창업학부")
                .build());
        departementRepository.save(Department.builder()
                .name("기계제어")
                .build());
        return ResponseEntity.ok(null);
    }
}
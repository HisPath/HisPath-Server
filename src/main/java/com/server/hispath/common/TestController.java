package com.server.hispath.common;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.activity.application.ActivityService;
import com.server.hispath.activity.domain.Activity;
import com.server.hispath.activity.domain.repository.ActivityRepository;
import com.server.hispath.auth.domain.*;
import com.server.hispath.category.domain.Category;
import com.server.hispath.category.domain.repository.CategoryRepository;
import com.server.hispath.department.domain.Department;
import com.server.hispath.department.domain.repository.DepartementRepository;
import com.server.hispath.exception.manager.ManagerNotFoundException;
import com.server.hispath.major.domain.Major;
import com.server.hispath.major.domain.repository.MajorRepository;
import com.server.hispath.manager.domain.Manager;
import com.server.hispath.manager.domain.repository.ManagerRepository;
import com.server.hispath.notice.domain.Notice;
import com.server.hispath.notice.domain.repository.NoticeRepository;
import com.server.hispath.resume.application.ResumeService;
import com.server.hispath.resume.domain.Resume;
import com.server.hispath.resume.domain.repository.ResumeRepository;
import com.server.hispath.scholarship.application.ScholarshipService;
import com.server.hispath.scholarship.domain.repository.ScholarshipRepository;
import com.server.hispath.student.application.StudentService;
import com.server.hispath.student.application.dto.StudentSimpleRefDto;
import com.server.hispath.student.domain.Student;
import com.server.hispath.student.domain.repository.StudentRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Builder;
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
    private final ActivityRepository activityRepository;
    private final StudentService studentService;
    private final ResumeRepository resumeRepository;
    private final ResumeService resumeService;
    private final ScholarshipRepository scholarshipRepository;
    private final ScholarshipService scholarshipService;

    @GetMapping("/init/all")
    public ResponseEntity<Void> initAll() {
        saveCategory();
        saveMajor();
        saveDepartment();
        saveStudent();
        saveManager();
        //        saveNotice();
//        saveActivities();
//        saveParticipant();
//        saveScholarship();
        //        saveResumes();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/init/category")
    public ResponseEntity<Void> initCategory() {
        saveCategory();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/init/department")
    public ResponseEntity<Void> initDepartment() {
        saveDepartment();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/init/notice")
    public ResponseEntity<Void> initNotice() {
        saveNotice();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/init/activity")
    public ResponseEntity<Void> initActivity() {
        saveActivities();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/init/manager")
    public ResponseEntity<Void> initManager() {

        saveManager();

        return ResponseEntity.ok(null);
    }

    @GetMapping("/init/major")
    public ResponseEntity<Void> initMajor() {
        saveMajor();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/init/student")
    public ResponseEntity<Void> initStudent() {
        saveStudent();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/init/participant")
    public ResponseEntity<Void> initParticipant() {
        saveParticipant();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/student/auth")
    @RequiredStudentLogin
    public ResponseEntity<Void> testStudentAuth(@StudentLogin LoginStudent loginStudent) {
        System.out.println(loginStudent.getId());
        System.out.println(loginStudent.getStudentNum());
        return ResponseEntity.ok(null);
    }

    @GetMapping("/manager/auth")
    @RequiredManagerLogin
    public ResponseEntity<Void> testManagerAuth(@ManagerLogin LoginManager loginManager) {
        System.out.println(loginManager.getId());
        return ResponseEntity.ok(null);
    }

    @GetMapping("init/resume")
    public ResponseEntity<Void> initResume() {

        saveResumes();
        return ResponseEntity.ok(null);
    }

    @GetMapping("init/scholarship")
    public ResponseEntity<Void> initScholarship() {

        saveScholarship();

        return ResponseEntity.ok(null);
    }

    private void saveScholarship() {
        List<Student> students = studentRepository.findAll();

        List<Long> studentIds2022_2 = students.stream()
                                              .map(Student::getId)
                                              .collect(Collectors.toList());
        Collections.shuffle(studentIds2022_2);
        int num2022_2 = getRandomNum(students.size() - 10, students.size());
        studentIds2022_2.subList(0, num2022_2)
                        .forEach(id -> {
                            scholarshipService.create(id, "2022-2");
                        });


        List<Long> studentIds2022_1 = students.stream()
                                              .map(Student::getId)
                                              .collect(Collectors.toList());
        Collections.shuffle(studentIds2022_1);
        int num2022_1 = getRandomNum(students.size() - 10, students.size());
        studentIds2022_1.subList(0, num2022_1)
                        .forEach(id -> {
                            scholarshipService.create(id, "2022-1");
                        });


        List<Long> studentIds2021_2 = students.stream()
                                              .map(Student::getId)
                                              .collect(Collectors.toList());
        Collections.shuffle(studentIds2021_2);
        int num2021_2 = getRandomNum(students.size() - 10, students.size());
        studentIds2021_2.subList(0, num2021_2)
                        .forEach(id -> {
                            scholarshipService.create(id, "2021-2");
                        });

    }

    private void saveResumes() {
        Student student = studentService.findById(1L);
        resumeRepository.save(Resume.builder()
                                    .student(student)
                                    .title("첫번째 이력서")
                                    .content("jsonData가 들어가야해")
                                    .build());
        resumeRepository.save(Resume.builder()
                                    .student(student)
                                    .title("두번째 이력서")
                                    .content("jsonData가 들어가야해")
                                    .build());
        resumeRepository.save(Resume.builder()
                                    .student(student)
                                    .title("세번째 이력서")
                                    .content("jsonData가 들어가야해")
                                    .build());
        resumeRepository.save(Resume.builder()
                                    .student(student)
                                    .title("네번째 이력서")
                                    .content("jsonData가 들어가야해")
                                    .build());
        resumeRepository.save(Resume.builder()
                                    .student(student)
                                    .title("다섯번째 이력서")
                                    .content("jsonData가 들어가야해")
                                    .build());
        resumeRepository.save(Resume.builder()
                                    .student(student)
                                    .title("여섯번째 이력서")
                                    .content("jsonData가 들어가야해")
                                    .build());
    }


    public int getRandomNum(int start, int end) {
        // Start 에서 End까지의 랜덤 수 출력
        return (int) (Math.random() * end - start) + start;
    }


    public void saveParticipant() {
        List<Activity> activities = activityRepository.findAll();
        List<Student> students = studentRepository.findAll();

        activities.forEach(activity -> {

            int num = getRandomNum(15, students.size());
            List<StudentSimpleRefDto> refStudent = students.stream()
                                                           .map(student -> {
                                                               return new StudentSimpleRefDto(student.getStudentNum(), student.getName());
                                                           })
                                                           .collect(Collectors.toList());

            Collections.shuffle(refStudent);
            studentService.registerParticipants(activity.getId(), refStudent.subList(0, num));
            activity.updateStudentRegister();
        });

    }

    private void saveManager() {
        managerRepository.save(Manager.builder()
                                      .name("김광 교수님")
                                      .email("kkim@handong.ac.kr")
                                      .department("CSEE")
                                      .approved(true).build());

        managerRepository.save(Manager.builder()
                                      .name("장소연 교수님")
                                      .email("jerry1004@handong.ac.kr")
                                      .department("CSEE")
                                      .approved(true).build());

        managerRepository.save(Manager.builder()
                                      .name("장기려 박사님")
                                      .email("janggiryeo@handong.ac.kr")
                                      .department("JGR")
                                      .approved(true).build());

        managerRepository.save(Manager.builder()
                                      .name("최도성 총장님")
                                      .email("cds@handong.ac.kr")
                                      .department("HGU")
                                      .approved(true).build());

        managerRepository.save(Manager.builder()
                                      .name("최경현 선생님")
                                      .email("cgh@handong.ac.kr")
                                      .department("SW")
                                      .approved(false).build());

        managerRepository.save(Manager.builder()
                                      .name("이윤정 선생님")
                                      .email("lyj@handong.ac.kr")
                                      .department("SW")
                                      .approved(false).build());

        managerRepository.save(Manager.builder()
                                      .name("이미나 선생님")
                                      .email("lmn@handong.ac.kr")
                                      .department("SW")
                                      .approved(true).build());
        managerRepository.save(Manager.builder()
                                      .name("안병웅")
                                      .email("BW_Ahn@handong.ac.kr")
                                      .department("전산전자공학부")
                                      .approved(true).build());

        managerRepository.save(Manager.builder()
                                      .name("박성진")
                                      .email("JakePark@handong.ac.kr")
                                      .department("글로벌리더쉽학부")
                                      .approved(true).build());

        managerRepository.save(Manager.builder()
                                      .name("홍성헌")
                                      .email("lukehongg@handong.ac.kr")
                                      .department("전산전자공학부")
                                      .approved(true).build());

        managerRepository.save(Manager.builder()
                                      .name("이인혁")
                                      .email("Bruse@handong.ac.kr")
                                      .department("ICT융합학부")
                                      .approved(true).build());

        managerRepository.save(Manager.builder()
                                      .name("정수산나")
                                      .email("Sanna@handong.ac.kr")
                                      .department("경영경제학부")
                                      .approved(false).build());

        managerRepository.save(Manager.builder()
                                      .name("정석민")
                                      .email("PeterJung@handong.ac.kr")
                                      .department("글로벌리더쉽학부")
                                      .approved(false).build());

        managerRepository.save(Manager.builder()
                                      .name("송다빈")
                                      .email("Emerson@handong.ac.kr")
                                      .department("전산전자공학부")
                                      .approved(true).build());
    }


    private void saveNotice() {
        List<Manager> l = new ArrayList<Manager>();
        for (Long i = Long.valueOf(1); i < 8; i++) {
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
    }


    private void saveStudent() {

        studentRepository.save(Student.builder()
                                      .name("박성진")
                                      .department(departementRepository.findByName("전산전자"))
                                      .studentNum("21700266")
                                      .semester(8)
                                      .major1(majorRepository.findByName("컴퓨터공학심화전공"))
                                      .major2(majorRepository.findByName("-"))
                                      .phone("010-9484-4321")
                                      .email("david@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@davidpiao.github")
                                      .loginCnt(0L)
                                      .readme("david's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("안병웅")
                                      .department(departementRepository.findByName("생명과학"))
                                      .studentNum("21600000")
                                      .semester(6)
                                      .major1(majorRepository.findByName("생명과학전공"))
                                      .major2(majorRepository.findByName("-"))
                                      .phone("010-1623-1512")
                                      .email("mh03@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@wooong.github")
                                      .loginCnt(0L)
                                      .readme("an's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("홍성헌")
                                      .department(departementRepository.findByName("커뮤니케이션"))
                                      .studentNum("21800929")
                                      .semester(8)
                                      .major1(majorRepository.findByName("공연영상학전공"))
                                      .major2(majorRepository.findByName("언로정보학전공"))
                                      .phone("010-1623-3322")
                                      .email("hong@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@hong.github")
                                      .loginCnt(0L)
                                      .readme("hong's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("이인혁")
                                      .department(departementRepository.findByName("기계제어"))
                                      .studentNum("21700032")
                                      .semester(5)
                                      .major1(majorRepository.findByName("전자제어공학전공"))
                                      .major2(majorRepository.findByName("기계공학전공"))
                                      .phone("010-4983-6555")
                                      .email("lee@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@ee.github")
                                      .loginCnt(0L)
                                      .readme("lee's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("정석민")
                                      .department(departementRepository.findByName("콘텐츠융합디자인"))
                                      .studentNum("22000432")
                                      .semester(3)
                                      .major1(majorRepository.findByName("시각디자인전공"))
                                      .major2(majorRepository.findByName("-"))
                                      .phone("010-4983-6555")
                                      .email("jeong@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@jeong.github")
                                      .loginCnt(0L)
                                      .readme("jeong's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("송다빈")
                                      .department(departementRepository.findByName("법학부"))
                                      .studentNum("22000332")
                                      .semester(3)
                                      .major1(majorRepository.findByName("US & International Law"))
                                      .major2(majorRepository.findByName("한국법전공"))
                                      .phone("010-7788-0142")
                                      .email("song@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@song.github")
                                      .loginCnt(0L)
                                      .readme("song's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("김한동")
                                      .department(departementRepository.findByName("전산전자"))
                                      .studentNum("22200000")
                                      .semester(5)
                                      .major1(majorRepository.findByName("컴퓨터공학전공"))
                                      .major2(majorRepository.findByName("생명과학전공"))
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
                                      .department(departementRepository.findByName("국제어문"))
                                      .studentNum("22200001")
                                      .semester(3)
                                      .major1(majorRepository.findByName("국제지역학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
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
                                      .department(departementRepository.findByName("경영경제"))
                                      .studentNum("22200002")
                                      .semester(5)
                                      .major1(majorRepository.findByName("경영학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
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
                                      .department(departementRepository.findByName("법학부"))
                                      .studentNum("22200003")
                                      .semester(6)
                                      .major1(majorRepository.findByName("한국법전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
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
                                      .department(departementRepository.findByName("커뮤니케이션"))
                                      .studentNum("22200004")
                                      .semester(6)
                                      .major1(majorRepository.findByName("공연영상학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
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
                                      .department(departementRepository.findByName("상담복지"))
                                      .studentNum("22200005")
                                      .semester(6)
                                      .major1(majorRepository.findByName("사회복지학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
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
                                      .department(departementRepository.findByName("공간환경시스템"))
                                      .studentNum("22200006")
                                      .semester(8)
                                      .major1(majorRepository.findByName("도시환경공학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
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
                                      .department(departementRepository.findByName("콘텐츠융합디자인"))
                                      .studentNum("22200007")
                                      .semester(4)
                                      .major1(majorRepository.findByName("컴퓨터공학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
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
                                      .department(departementRepository.findByName("기계제어"))
                                      .studentNum("22200008")
                                      .semester(6)
                                      .major1(majorRepository.findByName("기계공학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
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
                                      .department(departementRepository.findByName("ICT창업학부"))
                                      .studentNum("22200009")
                                      .semester(7)
                                      .major1(majorRepository.findByName("ICT 융합전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-8787-8787")
                                      .email("jin@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@jin.github")
                                      .loginCnt(0L)
                                      .readme("jin's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("김건휘")
                                      .department(departementRepository.findByName("전산전자"))
                                      .studentNum("21700234")
                                      .semester(2)
                                      .major1(majorRepository.findByName("컴퓨터공학전공"))
                                      .major2(majorRepository.findByName("생명과학전공"))
                                      .phone("010-1234-1234")
                                      .email("kim@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@kim.github")
                                      .loginCnt(0L)
                                      .readme("kim's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("이도경")
                                      .department(departementRepository.findByName("국제어문"))
                                      .studentNum("21200012")
                                      .semester(9)
                                      .major1(majorRepository.findByName("국제지역학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-1234-5678")
                                      .email("park@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@park.github")
                                      .loginCnt(0L)
                                      .readme("park's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("정민수")
                                      .department(departementRepository.findByName("경영경제"))
                                      .studentNum("22200032")
                                      .semester(5)
                                      .major1(majorRepository.findByName("경영학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-6655-4232")
                                      .email("lee@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@lee.github")
                                      .loginCnt(0L)
                                      .readme("lee's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("조수아")
                                      .department(departementRepository.findByName("법학부"))
                                      .studentNum("21600223")
                                      .semester(5)
                                      .major1(majorRepository.findByName("한국법전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-9292-9292")
                                      .email("jeong@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@jeong.github")
                                      .loginCnt(0L)
                                      .readme("jeong's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("김빛나리")
                                      .department(departementRepository.findByName("커뮤니케이션"))
                                      .studentNum("22200023")
                                      .semester(3)
                                      .major1(majorRepository.findByName("공연영상학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-2332-4333")
                                      .email("woo@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@woo.github")
                                      .loginCnt(0L)
                                      .readme("woo's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("김혜린")
                                      .department(departementRepository.findByName("상담복지"))
                                      .studentNum("21900021")
                                      .semester(4)
                                      .major1(majorRepository.findByName("사회복지학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-4444-1234")
                                      .email("wi@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@wi.github")
                                      .loginCnt(0L)
                                      .readme("wi's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("이소희")
                                      .department(departementRepository.findByName("공간환경시스템"))
                                      .studentNum("21800002")
                                      .semester(9)
                                      .major1(majorRepository.findByName("도시환경공학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-3221-6665")
                                      .email("ha@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@ha.github")
                                      .loginCnt(0L)
                                      .readme("ha's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("이소연")
                                      .department(departementRepository.findByName("콘텐츠융합디자인"))
                                      .studentNum("21400025")
                                      .semester(2)
                                      .major1(majorRepository.findByName("컴퓨터공학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-4211-1123")
                                      .email("ma@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@ma.github")
                                      .loginCnt(0L)
                                      .readme("ma's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("이동영")
                                      .department(departementRepository.findByName("기계제어"))
                                      .studentNum("21100234")
                                      .semester(10)
                                      .major1(majorRepository.findByName("기계공학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-2311-9992")
                                      .email("yu@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@yu.github")
                                      .loginCnt(0L)
                                      .readme("yu's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("이연진")
                                      .department(departementRepository.findByName("ICT창업학부"))
                                      .studentNum("21800012")
                                      .semester(9)
                                      .major1(majorRepository.findByName("ICT 융합전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-1234-0494")
                                      .email("jin@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@jin.github")
                                      .loginCnt(0L)
                                      .readme("jin's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("김시온")
                                      .department(departementRepository.findByName("전산전자"))
                                      .studentNum("22100032")
                                      .semester(2)
                                      .major1(majorRepository.findByName("컴퓨터공학전공"))
                                      .major2(majorRepository.findByName("생명과학전공"))
                                      .phone("010-3232-6767")
                                      .email("kim@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@kim.github")
                                      .loginCnt(0L)
                                      .readme("kim's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("황유민")
                                      .department(departementRepository.findByName("국제어문"))
                                      .studentNum("21600432")
                                      .semester(1)
                                      .major1(majorRepository.findByName("국제지역학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-4322-9009")
                                      .email("park@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@park.github")
                                      .loginCnt(0L)
                                      .readme("park's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("엄서영")
                                      .department(departementRepository.findByName("경영경제"))
                                      .studentNum("22000231")
                                      .semester(5)
                                      .major1(majorRepository.findByName("경영학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-6653-4231")
                                      .email("lee@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@lee.github")
                                      .loginCnt(0L)
                                      .readme("lee's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("이찬유")
                                      .department(departementRepository.findByName("법학부"))
                                      .studentNum("21600423")
                                      .semester(5)
                                      .major1(majorRepository.findByName("한국법전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-9292-9232")
                                      .email("jeong@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@jeong.github")
                                      .loginCnt(0L)
                                      .readme("jeong's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("천그루")
                                      .department(departementRepository.findByName("커뮤니케이션"))
                                      .studentNum("22200026")
                                      .semester(3)
                                      .major1(majorRepository.findByName("공연영상학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-2312-4343")
                                      .email("woo@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@woo.github")
                                      .loginCnt(0L)
                                      .readme("woo's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("이하민")
                                      .department(departementRepository.findByName("상담복지"))
                                      .studentNum("21900032")
                                      .semester(4)
                                      .major1(majorRepository.findByName("사회복지학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-4444-4234")
                                      .email("wi@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@wi.github")
                                      .loginCnt(0L)
                                      .readme("wi's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("박관희")
                                      .department(departementRepository.findByName("공간환경시스템"))
                                      .studentNum("21800232")
                                      .semester(9)
                                      .major1(majorRepository.findByName("도시환경공학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-3221-6365")
                                      .email("ha@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@ha.github")
                                      .loginCnt(0L)
                                      .readme("ha's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("현요섭")
                                      .department(departementRepository.findByName("콘텐츠융합디자인"))
                                      .studentNum("21400325")
                                      .semester(2)
                                      .major1(majorRepository.findByName("컴퓨터공학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-4211-1673")
                                      .email("ma@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@ma.github")
                                      .loginCnt(0L)
                                      .readme("ma's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("이찬호")
                                      .department(departementRepository.findByName("기계제어"))
                                      .studentNum("21100734")
                                      .semester(10)
                                      .major1(majorRepository.findByName("기계공학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-2911-9192")
                                      .email("yu@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@yu.github")
                                      .loginCnt(0L)
                                      .readme("yu's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("아무개")
                                      .department(departementRepository.findByName("ICT창업학부"))
                                      .studentNum("22200012")
                                      .semester(9)
                                      .major1(majorRepository.findByName("ICT 융합전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-4321-1275")
                                      .email("jin@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@jin.github")
                                      .loginCnt(0L)
                                      .readme("jin's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("김요나")
                                      .department(departementRepository.findByName("전산전자"))
                                      .studentNum("21100032")
                                      .semester(2)
                                      .major1(majorRepository.findByName("컴퓨터공학전공"))
                                      .major2(majorRepository.findByName("생명과학전공"))
                                      .phone("010-6731-1423")
                                      .email("kim@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@kim.github")
                                      .loginCnt(0L)
                                      .readme("kim's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("박도마")
                                      .department(departementRepository.findByName("국제어문"))
                                      .studentNum("21900432")
                                      .semester(1)
                                      .major1(majorRepository.findByName("국제지역학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-4222-3009")
                                      .email("park@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@park.github")
                                      .loginCnt(0L)
                                      .readme("park's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("이배드로")
                                      .department(departementRepository.findByName("경영경제"))
                                      .studentNum("22000331")
                                      .semester(5)
                                      .major1(majorRepository.findByName("경영학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-6653-9991")
                                      .email("lee@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@lee.github")
                                      .loginCnt(0L)
                                      .readme("lee's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("곽마태")
                                      .department(departementRepository.findByName("법학부"))
                                      .studentNum("21200423")
                                      .semester(5)
                                      .major1(majorRepository.findByName("한국법전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-1292-9132")
                                      .email("jeong@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@jeong.github")
                                      .loginCnt(0L)
                                      .readme("jeong's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("기운찬")
                                      .department(departementRepository.findByName("커뮤니케이션"))
                                      .studentNum("22100027")
                                      .semester(3)
                                      .major1(majorRepository.findByName("공연영상학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-2112-4243")
                                      .email("woo@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@woo.github")
                                      .loginCnt(0L)
                                      .readme("woo's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("곤잘레스")
                                      .department(departementRepository.findByName("상담복지"))
                                      .studentNum("21900112")
                                      .semester(4)
                                      .major1(majorRepository.findByName("사회복지학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-1144-4214")
                                      .email("wi@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@wi.github")
                                      .loginCnt(0L)
                                      .readme("wi's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("다비드")
                                      .department(departementRepository.findByName("공간환경시스템"))
                                      .studentNum("21800332")
                                      .semester(9)
                                      .major1(majorRepository.findByName("도시환경공학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-3331-6365")
                                      .email("ha@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@ha.github")
                                      .loginCnt(0L)
                                      .readme("ha's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("도베르만")
                                      .department(departementRepository.findByName("콘텐츠융합디자인"))
                                      .studentNum("21400425")
                                      .semester(2)
                                      .major1(majorRepository.findByName("컴퓨터공학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-4211-2673")
                                      .email("ma@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@ma.github")
                                      .loginCnt(0L)
                                      .readme("ma's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("진시황")
                                      .department(departementRepository.findByName("기계제어"))
                                      .studentNum("21100714")
                                      .semester(10)
                                      .major1(majorRepository.findByName("기계공학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-2931-9192")
                                      .email("yu@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@yu.github")
                                      .loginCnt(0L)
                                      .readme("yu's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("황유비")
                                      .department(departementRepository.findByName("ICT창업학부"))
                                      .studentNum("21900012")
                                      .semester(9)
                                      .major1(majorRepository.findByName("ICT 융합전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-4891-1275")
                                      .email("jin@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@jin.github")
                                      .loginCnt(0L)
                                      .readme("jin's readme")
                                      .build());

        studentRepository.save(Student.builder()
                                      .name("우요셉")
                                      .department(departementRepository.findByName("전산전자"))
                                      .studentNum("21900032")
                                      .semester(2)
                                      .major1(majorRepository.findByName("컴퓨터공학전공"))
                                      .major2(majorRepository.findByName("생명과학전공"))
                                      .phone("010-9191-1423")
                                      .email("kim@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@kim.github")
                                      .loginCnt(0L)
                                      .readme("kim's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("홍아담")
                                      .department(departementRepository.findByName("국제어문"))
                                      .studentNum("21901132")
                                      .semester(1)
                                      .major1(majorRepository.findByName("국제지역학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-9991-3009")
                                      .email("park@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@park.github")
                                      .loginCnt(0L)
                                      .readme("park's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("김이브")
                                      .department(departementRepository.findByName("경영경제"))
                                      .studentNum("22000131")
                                      .semester(5)
                                      .major1(majorRepository.findByName("경영학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-6653-9881")
                                      .email("lee@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@lee.github")
                                      .loginCnt(0L)
                                      .readme("lee's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("송삼손")
                                      .department(departementRepository.findByName("법학부"))
                                      .studentNum("21900423")
                                      .semester(5)
                                      .major1(majorRepository.findByName("한국법전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-1293-9832")
                                      .email("jeong@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@jeong.github")
                                      .loginCnt(0L)
                                      .readme("jeong's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("기윤호")
                                      .department(departementRepository.findByName("커뮤니케이션"))
                                      .studentNum("22100088")
                                      .semester(3)
                                      .major1(majorRepository.findByName("공연영상학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-2188-4243")
                                      .email("woo@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@woo.github")
                                      .loginCnt(0L)
                                      .readme("woo's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("김영찬")
                                      .department(departementRepository.findByName("상담복지"))
                                      .studentNum("21900232")
                                      .semester(4)
                                      .major1(majorRepository.findByName("사회복지학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-6644-4214")
                                      .email("wi@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@wi.github")
                                      .loginCnt(0L)
                                      .readme("wi's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("김영헌")
                                      .department(departementRepository.findByName("공간환경시스템"))
                                      .studentNum("21800232")
                                      .semester(9)
                                      .major1(majorRepository.findByName("도시환경공학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-3431-6365")
                                      .email("ha@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@ha.github")
                                      .loginCnt(0L)
                                      .readme("ha's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("김하은")
                                      .department(departementRepository.findByName("콘텐츠융합디자인"))
                                      .studentNum("21400825")
                                      .semester(2)
                                      .major1(majorRepository.findByName("컴퓨터공학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-4111-2673")
                                      .email("ma@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@ma.github")
                                      .loginCnt(0L)
                                      .readme("ma's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("장유진")
                                      .department(departementRepository.findByName("기계제어"))
                                      .studentNum("21100114")
                                      .semester(10)
                                      .major1(majorRepository.findByName("기계공학전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-2931-9192")
                                      .email("yu@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@yu.github")
                                      .loginCnt(0L)
                                      .readme("yu's readme")
                                      .build());
        studentRepository.save(Student.builder()
                                      .name("정수산나")
                                      .department(departementRepository.findByName("ICT창업학부"))
                                      .studentNum("21900312")
                                      .semester(9)
                                      .major1(majorRepository.findByName("ICT 융합전공"))
                                      .major2(majorRepository.findByName("컴퓨터공학전공"))
                                      .phone("010-4821-1175")
                                      .email("jin@handong.ac.kr")
                                      .profile("profile.url")
                                      .blog("blog.com")
                                      .githubId("@jin.github")
                                      .loginCnt(0L)
                                      .readme("jin's readme")
                                      .build());

    }

    private void saveCategory() {
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
    }


    private void saveDepartment() {
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
                                             .name("상담복지")
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
                                             .name("ICT융합학부")
                                             .build());
        departementRepository.save(Department.builder()
                                             .name("생명과학")
                                             .build());

    }


    public void saveActivities() {
        activityRepository.save(Activity.builder()
                                        .semester("2021-2")
                                        .personal(false)
                                        .remark("Spring 이용")
                                        .requestStatus(1)
                                        .name("(캠프)웹서비스 프로젝트(spring)_장소연")
                                        .weight(3)
                                        .category(categoryRepository.findByName("비교과-행사참여"))
                                        .build());
        activityRepository.save(Activity.builder()
                                        .semester("2022-2")
                                        .personal(false)
                                        .remark("우수상 수상")
                                        .requestStatus(1)
                                        .name("비즈플로우")
                                        .weight(2)
                                        .category(categoryRepository.findByName("비교과-행사참여"))
                                        .build());
        activityRepository.save(Activity.builder()
                                        .semester("2020-2")
                                        .personal(false)
                                        .remark("")
                                        .requestStatus(1)
                                        .name("(캠프)미리미리C 캠프_김광")
                                        .weight(5)
                                        .category(categoryRepository.findByName("비교과-행사참여"))
                                        .build());
        activityRepository.save(Activity.builder()
                                        .semester("2020-1")
                                        .personal(false)
                                        .remark("")
                                        .requestStatus(1)
                                        .name("C 프로그래밍")
                                        .weight(1)
                                        .category(categoryRepository.findByName("전공마일리지"))
                                        .build());
        activityRepository.save(Activity.builder()
                                        .semester("2021-1")
                                        .personal(false)
                                        .remark("")
                                        .requestStatus(1)
                                        .name("AI프로젝트 입문")
                                        .weight(2)
                                        .category(categoryRepository.findByName("전공마일리지"))
                                        .build());
        activityRepository.save(Activity.builder()
                                        .semester("2019-2")
                                        .personal(false)
                                        .remark("")
                                        .requestStatus(1)
                                        .name("(캠프)Advanced Flutter Camp_조성배")
                                        .weight(4)
                                        .category(categoryRepository.findByName("비교과-행사참여"))
                                        .build());
        activityRepository.save(Activity.builder()
                                        .semester("2019-1")
                                        .personal(true)
                                        .remark("")
                                        .requestStatus(1)
                                        .name("정보처리기사 자격증")
                                        .weight(2)
                                        .category(categoryRepository.findByName("기타"))
                                        .build());
        activityRepository.save(Activity.builder()
                                        .semester("2022-1")
                                        .personal(false)
                                        .remark("")
                                        .requestStatus(1)
                                        .name("공학프로젝트기획")
                                        .weight(6)
                                        .category(categoryRepository.findByName("전공마일리지"))
                                        .build());
        activityRepository.save(Activity.builder()
                                        .semester("2020-2")
                                        .personal(false)
                                        .remark("")
                                        .requestStatus(1)
                                        .name("캡스톤")
                                        .weight(1)
                                        .category(categoryRepository.findByName("전공마일리지"))
                                        .build());
        activityRepository.save(Activity.builder()
                                        .semester("2021-2")
                                        .personal(false)
                                        .remark("")
                                        .requestStatus(1)
                                        .name("프로그래밍 집중훈련 캠프_김호준")
                                        .weight(7)
                                        .category(categoryRepository.findByName("비교과-행사참여"))
                                        .build());
    }


    private void saveMajor() {
        majorRepository.save(Major.builder().name("-").build());
        majorRepository.save(Major.builder().name("건설공학전공").build());
        majorRepository.save(Major.builder().name("도시환경공학전공").build());
        majorRepository.save(Major.builder().name("기계공학전공").build());
        majorRepository.save(Major.builder().name("전자제어공학전공").build());
        majorRepository.save(Major.builder().name("생명과학전공").build());
        majorRepository.save(Major.builder().name("글로벌융합전공").build());
        majorRepository.save(Major.builder().name("수학통계전공").build());
        majorRepository.save(Major.builder().name("학생설계융합전공").build());
        majorRepository.save(Major.builder().name("시각디자인전공").build());
        majorRepository.save(Major.builder().name("제품디자인전공").build());
        majorRepository.save(Major.builder().name("컴퓨터공학전공").build());
        majorRepository.save(Major.builder().name("컴퓨터공학심화전공").build());
        majorRepository.save(Major.builder().name("전자공학전공").build());
        majorRepository.save(Major.builder().name("전자공학심화전공").build());
        majorRepository.save(Major.builder().name("Information Technology").build());
        majorRepository.save(Major.builder().name("ICT 융합전공").build());
        majorRepository.save(Major.builder().name("AI Convergence & Entrepreneurship").build());
        majorRepository.save(Major.builder().name("Global Entrepreneurship").build());
        majorRepository.save(Major.builder().name("AI 융합").build());
        majorRepository.save(Major.builder().name("데이터 사이언스 전공").build());
        majorRepository.save(Major.builder().name("경영학전공").build());
        majorRepository.save(Major.builder().name("경제학전공").build());
        majorRepository.save(Major.builder().name("Global Management").build());
        majorRepository.save(Major.builder().name("국제지역학전공").build());
        majorRepository.save(Major.builder().name("영어전공").build());
        majorRepository.save(Major.builder().name("한국법전공").build());
        majorRepository.save(Major.builder().name("US & International Law").build());
        majorRepository.save(Major.builder().name("상담심리학전공").build());
        majorRepository.save(Major.builder().name("사회복지학전공").build());
        majorRepository.save(Major.builder().name("공연영상학전공").build());
        majorRepository.save(Major.builder().name("언로정보학전공").build());
        majorRepository.save(Major.builder().name("글로벌융합전공").build());
        majorRepository.save(Major.builder().name("학생설계융합전공").build());
        majorRepository.save(Major.builder().name("글로벌한국학전공").build());
    }
}
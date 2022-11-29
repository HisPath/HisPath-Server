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
        saveNotice();
        saveActivities();
//        saveScholarship(); participant 실행 한 뒤 실행
//        saveParticipant(); init all에 포함 금지! 따로 실행
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

        List<Long> studentIds = students.stream()
                .map(Student::getId)
                .collect(Collectors.toList());

        studentIds.forEach(id -> scholarshipService.create(id, "2022-2"));
        studentIds.forEach(id -> scholarshipService.create(id, "2022-1"));
        studentIds.forEach(id -> scholarshipService.create(id, "2021-2"));


        //        List<Long> studentIds2022_2 = students.stream()
        //                .map(Student::getId)
        //                .collect(Collectors.toList());
        //        Collections.shuffle(studentIds2022_2);
        //        int num2022_2 = getRandomNum(students.size() - 10, students.size());
        //        studentIds2022_2.subList(0, num2022_2)
        //                .forEach(id -> {
        //                    scholarshipService.create(id, "2022-2");
        //                });
        //
        //
        //        List<Long> studentIds2022_1 = students.stream()
        //                .map(Student::getId)
        //                .collect(Collectors.toList());
        //        Collections.shuffle(studentIds2022_1);
        //        int num2022_1 = getRandomNum(students.size() - 10, students.size());
        //        studentIds2022_1.subList(0, num2022_1)
        //                .forEach(id -> {
        //                    scholarshipService.create(id, "2022-1");
        //                });
        //
        //
        //        List<Long> studentIds2021_2 = students.stream()
        //                .map(Student::getId)
        //                .collect(Collectors.toList());
        //        Collections.shuffle(studentIds2021_2);
        //        int num2021_2 = getRandomNum(students.size() - 10, students.size());
        //        studentIds2021_2.subList(0, num2021_2)
        //                .forEach(id -> {
        //                    scholarshipService.create(id, "2021-2");
        //                });

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

            //            int num = getRandomNum(15, students.size());
            List<StudentSimpleRefDto> refStudent = students.stream()
                    .map(student -> {
                        return new StudentSimpleRefDto(student.getStudentNum(), student.getName());
                    })
                    .collect(Collectors.toList());

            studentService.registerParticipants(activity.getId(), refStudent);
            //            Collections.shuffle(refStudent);
            //            studentService.registerParticipants(activity.getId(), refStudent.subList(0, num));
            activity.updateStudentRegister();
        });

    }

    private void saveManager() {
        managerRepository.save(Manager.builder()
                .name("이정재 교수님")
                .email("kkim@handong.ac.kr")
                .department("CSEE")
                .profile("https://user-images.githubusercontent.com/63008958/203914314-2eb9a33d-efab-4f08-80cf-ce00757d232a.jpeg")
                .approved(true).build());

        managerRepository.save(Manager.builder()
                .name("아이유 교수님")
                .email("jerry1004@handong.ac.kr")
                .department("CSEE")
                .profile("https://user-images.githubusercontent.com/63008958/203914299-6d707193-28ab-457c-8d03-51efb758072d.jpeg")
                .approved(true).build());

        managerRepository.save(Manager.builder()
                .name("송중기 박사님")
                .email("janggiryeo@handong.ac.kr")
                .department("JGR")
                .profile("https://user-images.githubusercontent.com/63008958/203914292-50d8abb3-8e7a-45c1-90ca-22bc156b1165.jpeg")
                .approved(true).build());

        managerRepository.save(Manager.builder()
                .name("공유 총장님")
                .email("cds@handong.ac.kr")
                .department("HGU")
                .profile("https://user-images.githubusercontent.com/63008958/203914306-5dbc47d3-ae07-4314-8708-abef0fcdd7c1.jpeg")
                .approved(true).build());

        managerRepository.save(Manager.builder()
                .name("김태리 선생님")
                .email("cgh@handong.ac.kr")
                .department("SW")
                .profile("https://user-images.githubusercontent.com/63008958/203914310-b00e05a7-c827-4247-945c-737ac56eeaf1.jpeg")
                .approved(false).build());

        managerRepository.save(Manager.builder()
                .name("이지은 선생님")
                .email("lyj@handong.ac.kr")
                .department("SW")
                .profile("https://user-images.githubusercontent.com/63008958/203914295-2fc460fe-3998-4f3c-b298-fff9a6c5676a.jpeg")
                .approved(false).build());

        managerRepository.save(Manager.builder()
                .name("손예진 선생님")
                .email("lmn@handong.ac.kr")
                .department("SW")
                .profile("https://user-images.githubusercontent.com/63008958/203914281-9c0acb13-562b-45df-a16a-61015f6881ff.png")
                .approved(true).build());
        managerRepository.save(Manager.builder()
                .name("안병웅 관리자")
                .email("BW_Ahn@handong.ac.kr")
                .department("전산전자공학부")
                .profile("https://user-images.githubusercontent.com/63008958/203915182-c8216b87-9e06-4a10-8efe-b8d0cb43b5af.png")
                .approved(true).build());

        managerRepository.save(Manager.builder()
                .name("박성진 괸리자")
                .email("david@handong.ac.kr")
                .department("글로벌리더쉽학부")
                .profile("https://user-images.githubusercontent.com/63008958/203915214-b9d6ffef-b011-43a4-9a9b-bf160f88b07c.png")
                .approved(true).build());

        managerRepository.save(Manager.builder()
                .name("홍성헌")
                .email("lukehongg@handong.ac.kr")
                .department("전산전자공학부")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .approved(true).build());

        managerRepository.save(Manager.builder()
                .name("이인혁")
                .email("Bruse@handong.ac.kr")
                .department("ICT 융합학부")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .approved(true).build());

        managerRepository.save(Manager.builder()
                .name("정수산나")
                .email("Sanna@handong.ac.kr")
                .department("경영경제학부")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .approved(false).build());

        managerRepository.save(Manager.builder()
                .name("정석민")
                .email("PeterJung@handong.ac.kr")
                .department("글로벌리더쉽학부")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .approved(false).build());

        managerRepository.save(Manager.builder()
                .name("송다빈")
                .email("Emerson@handong.ac.kr")
                .department("전산전자공학부")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .approved(true).build());
    }


    private void saveNotice() {
        List<Manager> l = new ArrayList<Manager>();
        for (Long i = Long.valueOf(1); i < 8; i++) {
            if(i != 5 && i != 6){
                Manager temp = managerRepository.findById(i).orElseThrow(ManagerNotFoundException::new);
                l.add(temp);
            }
        }

        noticeRepository.save(Notice.builder()
                .manager(l.get(1))
                .title("원클릭 컴공 전공 상담신청 서비스 안내")
                .content("<p>상담 받기 좋은 계절입니다.</p><p>&nbsp;</p><p>질문거리, 고민거리는 있지만 어느 교수님께 물어야 할 지 모르겠다면...</p><p>교수님께 상담 요청 이메일, 어떻게 써야 할지 몰라 망설여지신다면...</p><p>편하게 이것저것 여쭤보고 싶은데 안면 있는 교수님이 없어 막막하시다면...</p><p>&nbsp;</p><p>원클릭 컴공 전공상담신청을 이용해보시기 바랍니다!</p><p>&nbsp;</p><p>&nbsp;<a href=\"https://forms.gle/n8mvzfJ51Ahzaujz8\">https://forms.gle/n8mvzfJ51Ahzaujz8</a></p><p>&nbsp;</p><p>접수한 내용을 바탕으로 가능한 빨리 적절한 교수님을 찾아 매칭해드립니다.&nbsp;</p><p>&nbsp;</p><p>학생 이메일 도메인 변경에 따라 Google Form 주소도 업데이트 했으니</p><p>참고하시기 바랍니다.</p><p>&nbsp;</p><p>- 홍신</p>")
                .viewCnt(0)
                .importance(true)
                .pubDate(LocalDate.of(2022, Month.NOVEMBER, 19))
                .expDate(LocalDate.of(2023, Month.APRIL, 26))
                .build());

        noticeRepository.save(Notice.builder()
                .manager(l.get(2))
                .title("2022 BizFlow M Pre-Contest (신청기간연장 ~6.7(화))")
                .content("\"<p>■ &nbsp;<strong>접수기간</strong>&nbsp;: 2022년 5월 25일 (수) ~ 6월 7일 (화)</p><p>&nbsp;</p><p>&nbsp;■<strong>&nbsp;&nbsp;접수링크</strong>&nbsp;:<strong>&nbsp;<a target='_blank' href=\"https://docs.google.com/forms/d/1BR9IKalGSdDPKR4Hi6dvW8dpL57dKhvfz0oliJatRno/edit\">https://docs.google.com/forms/d/1BR9IKalGSdDPKR4Hi6dvW8dpL57dKhvfz0oliJatRno/edit</a></strong></p><p><strong>&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</strong>&nbsp;</p><p>&nbsp;■ &nbsp;<strong>참가자격</strong></p><p>&nbsp; &nbsp; &nbsp; 한동대학교 재학생으로서 다음 사항 중 1개라도 해당되는 팀 (휴학생 팀원 참여 가능)</p><p>&nbsp; &nbsp; &nbsp; 1) 팀원 중 BizFlow Camp 수료자가 있는 경우</p><p>&nbsp; &nbsp; &nbsp; 2) 팀원 중 이전 BizFlow AppDev Contest 수상자(참가상 포함)가 있는 경우</p><p>&nbsp; &nbsp; &nbsp; 3) 팀원 중 BizFlow AppDev 사용 경험자가 있는 경우</p><p>&nbsp; &nbsp; &nbsp; 4) 팀원 모두 BizFlow AppDev 사용 경험은 없지만, 웹사이트 제작 경험이 있는 경우&nbsp;</p><p>&nbsp;</p><p>&nbsp;■&nbsp;<strong>컨테스트 내용&nbsp;</strong>&nbsp;&nbsp;</p><p>&nbsp; &nbsp; &nbsp;。사전 제시된 미션을 BizFlow M을 사용하여 구현한 후, 기한 내에 제출함&nbsp;</p><p>&nbsp; &nbsp; &nbsp;。<strong>미션 링크</strong>&nbsp;:<a href=\"https://www.notion.so/HGU-X-AppDev-2022-Precontest-473e3aed71554cf2899238fd49ca3d6b\">&nbsp;https://www.notion.so/HGU-X-AppDev-2022-Precontest-473e3aed71554cf2899238fd49ca3d6b</a></p><p>&nbsp; &nbsp; &nbsp;。<strong>컨테스트 미션에 대한 설명 영상 :&nbsp;</strong><a href=\"https://youtu.be/od_lQFIrj7g\">https://youtu.be/od_lQFIrj7g</a></p><p>&nbsp; &nbsp; &nbsp;。이번 컨테스트 진행 중 질문답변을 위해 다음 링크를 사용하기 바랍니다.&nbsp; &nbsp;&nbsp;</p><p>&nbsp; &nbsp; &nbsp; &nbsp;<strong>클라썸 입장 링크</strong>:&nbsp;<a href=\"http://www.classum.com/EXSTQA\">www.classum.com/EXSTQA</a>&nbsp;</p><p>&nbsp;</p><p>&nbsp;■<strong>&nbsp;팀 구성 : 1~3인으로 팀 구성 (개인 참가 가능)</strong></p><p><br></p><p><br></p><p><strong>&nbsp;■&nbsp;수상팀에게는 여름방학 중에 진행되는 본 컨테스트 출전 자격이 주어집니다.</strong></p><p><strong>&nbsp; &nbsp;&nbsp;</strong>1) 최우수상 : 1팀 60만원</p><p>&nbsp; &nbsp; &nbsp;2) 우수상 : 5팀 내외 40만원</p><p>&nbsp; &nbsp; &nbsp;3) 장려상 : 10팀 내외 20만원</p><p>&nbsp; &nbsp; &nbsp;4) 참가상 : 기념품 지급</p><p><br />  <img width='70%' height='auto' style='display: block; margin: 0 auto' src='https://user-images.githubusercontent.com/79990740/203981522-4ca8d917-7964-4e6f-a322-aadd13c8200e.png' contenteditable=\"false\" /> <br /></p>\"")
                .viewCnt(0)
                .importance(true)
                .pubDate(LocalDate.of(2022, Month.NOVEMBER, 24))
                .expDate(LocalDate.of(2023, Month.MAY, 30))
                .build());

        noticeRepository.save(Notice.builder()
                .manager(l.get(3))
                .title("2022-2 사전 학점인정 안내(타대학 교환·학점교류)")
                .content("<p>22-2학기 타대학 교환·학점교류를 통해 전산전자공학부공학부의 컴퓨터공학(심화), 전자공학(심화) 전공 학점으로 인정받고자 하는 학생은, 아래의 내용을 확인하시고 사전학점인정원 제출하시기 바랍니다.</p><p><br></p><p><br></p><p><strong>1. 신청기간</strong>: ~2022.8.14.</p><p>&nbsp; &nbsp; &nbsp;* 각 대학마다 수강신청기간이 다르므로, 본인 대학 수강신청기간 전에 신청 바람</p><p><br></p><p><br></p><p><strong>2. 제출서류</strong>: 사전학점인정원, 해당 과목 강의계획서</p><p><br></p><p><br></p><p><strong>3. 제출방법</strong>: csee@handong.edu로 온라인 제출</p><p><br></p><p><br></p><p><strong>4. 사전학점인정 절차</strong></p><p>&nbsp; 가. 학생이 학부사무실로 사전학점인정원 제출</p><p>&nbsp; 나. 해당 전공의 프로그램위원회에서 제출된 자료 검토하여 학점인정 여부 판단</p><p>&nbsp; 다. 검토완료 된 사전학점인정원을 학부사무실에서 학생 메일로 송부</p><p><br></p><p><br></p><p><strong>5. 유의사항</strong></p><p>&nbsp; 가.&nbsp;각 대학마다 수강신청기간이 다르므로, 사전학점인정 승인 받은 후 수강신청 할 수 있도록 시간 여유를 두고 미리 신청 바람</p><p>&nbsp; 나. 본인서명 필수 /&nbsp; 담당교수 확인란은 비워 제출</p><p>&nbsp; 다. '해당전공'란은 컴퓨터공학 or 전자공학을 기입</p><p>&nbsp; 라.&nbsp;이전 학기에 학점교류를 통해 전공 인정받은 학생의 경우 아래 내용 함께 제출</p><p>&nbsp; &nbsp; 1) 이전 교류대학명:&nbsp;</p><p>&nbsp; &nbsp; 2) 인정 과목명:</p><p>&nbsp; &nbsp; 3) 총 인정학점:</p><p>&nbsp; 마. 사전학점인정원 승인을 받았더라도, 다른 수강 교과목과 내용이 중복되는 경우 추후 학점인정을 받지 못할 수 있음</p><p><br></p><p><br></p><p><br></p><p><br></p><p>문의 : 260-1414 / csee@handong.edu</p>")
                .viewCnt(0)
                .importance(false)
                .pubDate(LocalDate.of(2022, Month.NOVEMBER, 2))
                .expDate(LocalDate.of(2023, Month.JUNE, 15))
                .build());

        noticeRepository.save(Notice.builder()
                .manager(l.get(4))
                .title("2022년 8월 졸업생 학위증 및 상장 교부 안내")
                .content("<p>9/2(금) 15시까지 신청 건 우편 발송되었습니다.&nbsp;</p><p>&nbsp;</p><p>---------------------------------------------------------------------------</p><p>&nbsp;</p><p>안녕하세요?</p><p>전산전자공학부공학부입니다.</p><p><br></p><p><br></p><p>졸업을 진심으로 축하드립니다.\uD83C\uDF89</p><p><br></p><p><br></p><p>학위증은&nbsp;<strong>8/17(수)부터 학부사무실(NTH309)</strong>에서 직접 수령 가능합니다.</p><p><br></p><p><br></p><p>우편(착불 4500원 정도)으로 받고자 하는 학생은&nbsp;</p><p><strong>9/4(일)까지 구글폼(&nbsp;<a href=\"https://forms.gle/YC33x2dgMp2o6FFP7\">https://forms.gle/YC33x2dgMp2o6FFP7</a>&nbsp;) 작성</strong>해주시기 바랍니다.</p><p><br></p><p><br></p><p>이전 졸업생 중 학위증을 수령하지 않은 학생도 신청하시기 바랍니다.</p><p><br></p><p><br></p><p>&lt;유의사항&gt;</p><p>1. 학위증은 23년 2월 학위수여식까지 보관하며, 그 후 분실에 대해 책임지지 않습니다.</p><p>2. 학위증 재발급은 불가하니, 꼭 수령하시기 바랍니다.</p><p><br></p><p><br></p><p>감사합니다:D</p><p><br></p><p><br></p><p>문의) 전산전자공학부공학부사무실 260-1414</p>")
                .viewCnt(0)
                .importance(true)
                .pubDate(LocalDate.of(2022, Month.NOVEMBER, 7))
                .expDate(LocalDate.of(2023, Month.JUNE, 29))
                .build());

        noticeRepository.save(Notice.builder()
                .manager(l.get(4))
                .title("2022년 8월 졸업생 학위증 및 상장 교부 안내")
                .content("<p>9/2(금) 15시까지 신청 건 우편 발송되었습니다.&nbsp;</p><p>&nbsp;</p><p>---------------------------------------------------------------------------</p><p>&nbsp;</p><p>안녕하세요?</p><p>전산전자공학부공학부입니다.</p><p><br></p><p><br></p><p>졸업을 진심으로 축하드립니다.\uD83C\uDF89</p><p><br></p><p><br></p><p>학위증은&nbsp;<strong>8/17(수)부터 학부사무실(NTH309)</strong>에서 직접 수령 가능합니다.</p><p><br></p><p><br></p><p>우편(착불 4500원 정도)으로 받고자 하는 학생은&nbsp;</p><p><strong>9/4(일)까지 구글폼(&nbsp;<a href=\"https://forms.gle/YC33x2dgMp2o6FFP7\">https://forms.gle/YC33x2dgMp2o6FFP7</a>&nbsp;) 작성</strong>해주시기 바랍니다.</p><p><br></p><p><br></p><p>이전 졸업생 중 학위증을 수령하지 않은 학생도 신청하시기 바랍니다.</p><p><br></p><p><br></p><p>&lt;유의사항&gt;</p><p>1. 학위증은 23년 2월 학위수여식까지 보관하며, 그 후 분실에 대해 책임지지 않습니다.</p><p>2. 학위증 재발급은 불가하니, 꼭 수령하시기 바랍니다.</p><p><br></p><p><br></p><p>감사합니다:D</p><p><br></p><p><br></p><p>문의) 전산전자공학부공학부사무실 260-1414</p>")
                .viewCnt(0)
                .importance(true)
                .pubDate(LocalDate.of(2022, Month.NOVEMBER, 21))
                .expDate(LocalDate.of(2023, Month.JUNE, 27))
                .build());

        noticeRepository.save(Notice.builder()
                .manager(l.get(3))
                .title("[BK21 인공지능 교육연구단] 2022-2 참여대학원생 등록서류 제출 및 연구장학금 신청 안내")
                .content("<p>한동대학교&nbsp;BK21&nbsp;인공지능 사업단</p><p>2022학년도 2학기 참여대학원생 등록서류 제출 및 연구장학금 신청 안내</p><p>&nbsp;</p><p>&nbsp;</p><p>한동대학교 전산전자공학부공학과&nbsp;4단계&nbsp;BK21 ‘산업혁신을 위한&nbsp;AI&nbsp;고급 인재교육연구단’ (BK21&nbsp;인공지능사업단)에서 다음과 같이 등록 서류를 제출하여주시기 바랍니다.</p><p>&nbsp;</p><p><br></p><p>1. 4단계&nbsp;BK21&nbsp;참여대학원생 기준 (다음의 조건을 모두 충족한 자)</p><p>BK21&nbsp;참여교수의 지도학생 중 전일제(4대보험에 가입되어 있지 않은 학생)로 등록(연구생 등록 포함)한 석·박사과정 대학원생으로 다음 각 호에 해당하는 자</p><p>-입학한 지&nbsp;2년이 지나지 않은 석사과정생</p><p>-입학한 지&nbsp;4년이 지나지 않은 박사과정생 및 박사 수료생</p><p>-입학한 지&nbsp;6년이 지나지 않은 석박통합과정생 및 석박통합과정 수료생</p><p>(위의 기간 기산 시 휴학 및 군복무 기간은 제외함).</p><p>&nbsp;</p><p>위의 자격을 충족한 경우&nbsp;‘참여대학원생’으로 등록되어&nbsp;‘지원대학원생’&nbsp;선발 기회 등&nbsp;BK21&nbsp;사업의 지원을 받을 수 있음.</p><p>&nbsp;</p><p>2.&nbsp;지원대학원생 선발 및 연구장학금 지급</p><p>1)&nbsp;참여대학원생으로 등록된 학생 중&nbsp;70%&nbsp;이내의 대학원생을&nbsp;‘지원대학원생’으로 선발해 연구장학금을 지급함.</p><p>2)&nbsp;지원기간: 2022.09.01.~2023.02.28. (6개월 단위로 선발)</p><p>3)&nbsp;선발기준:&nbsp;<strong>지도교수 추천을 받은 참여대학원생&nbsp;</strong>중 연구/프로젝트 성과 평가에 의해 선발</p><p>4)&nbsp;역할 및 임무</p><p>- BK21&nbsp;참여교수 연구실에서 논문 연구 및 산학 프로젝트를 성실히 수행&nbsp;(주&nbsp;40시간 이상)</p><p>-&nbsp;사업단이 주관하는 각종 세미나,&nbsp;포럼 등의 학술 활동에 참여</p><p>-&nbsp;사업단이 요구하는 성과 자료 보고</p><p>&nbsp;</p><p><br></p><p>3.&nbsp;참여대학원생 등록서류 제출안내(등록서류는 단순참여, 장학금 지원 받는 학생 모두 제출해주세요.)</p><p>1)&nbsp;첨부된 제출서류 다운 및 작성 후&nbsp;BK21&nbsp;사업단 사무실로 서류 제출.</p><p>2)&nbsp;지도교수로부터 지원대학원생(장학금 받는 학생)으로 추천받은 대학원생은&nbsp;<strong>5.제출서류</strong>&nbsp;함께 제출</p><p>&nbsp;</p><p>4.&nbsp;서류 제출 기간:&nbsp;2022년 8월 29일(월) 13:00 - 9월 7일(수) 17:00 (제출기한 엄수)</p><p><br></p><p><br></p><p><br></p><p>5.&nbsp;제출서류</p><p>-&nbsp;연구자등록번호 발급 방법</p><p>: KRI(한국연구업적통합정보시스템&nbsp;<a href=\"http://www.kri.go.kr%29/\">http://www.kri.go.kr)</a>에 접속 회원가입→연구자등록번호 발급&nbsp;</p><p>&nbsp; (가입 시 소속을 반드시&nbsp;‘한동대학교’로 가입)&nbsp;</p><p><br></p><p><br></p><p>1)&nbsp;재학증명서&nbsp;1부.&nbsp;연구생등록자의 경우 연구생등록증&nbsp;1부(2022년 9월 1일자 기준으로 제출)</p><p>2)&nbsp;개인정보 수집 이용 제공 동의서&nbsp;1부. (BK사업에 처음 참여하는 학생만 제출)</p><p>3)&nbsp;참여인력 서약서&nbsp;1부.&nbsp;</p><p>4)&nbsp;참여대학원생 확약서&nbsp;1부.</p><p>5) BK21&nbsp;연구장학금 신청서&nbsp;1부. (지원대학원생-BK장학금 받는 학생만 제출)</p><p>6) 전산전자공학부공학과 학기 보고서&nbsp;1부.</p><p>7)&nbsp;재학증명서&nbsp;1부.(9월 1일 기준)</p><p>&nbsp;</p><p><strong>8) 연구재단 필수 제출 서류&nbsp;'4대 보험 가입 증명서'&nbsp;1부.&nbsp;(22.10.1일 기준일로하여 메일로 회신)</strong></p><p><strong>(**10월1일 기준으로 출력&nbsp;후 제출 바랍니다. 9월에는 제출하지 않아도 됩니다!)</strong></p><p>-&nbsp;출력 방법: 4대 사회보험 포털서비스(<a href=\"http://www.4insure.or.kr%29/\">http://www.4insure.or.kr)</a> 로그인(회원가입,&nbsp;공인인증서 필요)&nbsp;→&nbsp;상단 메뉴 증명서발급(가입내역확인)&nbsp;클릭 후 출력</p><p>&nbsp;</p><p>9)&nbsp;대학원 성적표&nbsp;1부. (신입생은 제출X)</p><p>&nbsp;</p><p>&lt;한국연구재단 필수 수강!!-참여기간 중 1회 수강 후 수료증 제출해야함, 교육 미이수자 및&nbsp; 22-2학기 신입생만 수강하면 됩니다.&gt;</p><p>10)&nbsp;온라인 교육 수료증&nbsp;(<a href=\"http://alpha-campus.kr/)\">http://alpha-campus.kr/)</a>&nbsp;에 접속하여 로그인 후 상단 메뉴의 '탐색'-&gt;'온라인교육' -&gt; '건강한 연구환경 조성을 위한 인권침해예방교육'클릭 -&gt;'수강신청' 클릭 후 팝업창에서 수강신청 정보 확인 및 '신청완료'-&gt; '학습'-&gt; '학습중과정'-&gt;&nbsp;'건강한 연구환경 조성을 위한 인권침해예방교육' 순서대로 클릭 후 '학습하기'</p><p>컨텐츠 수강 후&nbsp;‘MY'-&gt; '학습이력'클릭 후 수료증 발급 및 보관)&nbsp;</p><p>*수료증은 메일로 회신부탁합니다.&nbsp;</p><p>&nbsp;</p><p>11)&nbsp;통장사본 및 신분증 사본 각&nbsp;1부.</p><p>&nbsp;</p><p>※&nbsp;외국인 참여대학원생의 경우 외국인 등록증 앞,&nbsp;뒤 사본(D-2비자 여부 및 만료기한 확인)&nbsp;제출.</p><p>※ 기존&nbsp;참여대학원생이 지원할 경우: 2)개인정보 수집 이용 제공 동의서,&nbsp;10)온라인 교육 수료증 제출 생략.</p><p>&nbsp;</p><p>6.&nbsp;서류 제출 및 문의처</p><p>1) BK21사업단 사무실:&nbsp;뉴턴홀&nbsp;309호 전산전자공학부공학부 사무실&nbsp;담당자 고라경(Tel: 260-3150)</p><p>2)&nbsp;신청 시 모든 서류는 위 제출서류 순서대로 정리하여 원본 제출 바랍니다.</p><p>&nbsp;</p><p>7.&nbsp;유의사항:&nbsp;제출된 서류는 반환하지 않으며,&nbsp;허위사실 기재 시 선발을 취소함.</p>")
                .viewCnt(0)
                .importance(false)
                .pubDate(LocalDate.of(2022, Month.JULY, 1))
                .expDate(LocalDate.of(2022, Month.JULY, 14))
                .build());

        noticeRepository.save(Notice.builder()
                .manager(l.get(2))
                .title("2022 한동 SW페스티벌 수상자 안내 및 서류 제출 요청(10월 19일(수)까지)")
                .content("<p><strong>2022 한동 SW페스티벌 수상자 안내 및 서류 제출 요청(10월 19일(수)까지)</strong></p><p>&nbsp;&nbsp;</p><p>'2022 한동 SW페스티벌'에 참여한 모든 참가자들에게 감사드리며&nbsp;수상팀을 안내드립니다.&nbsp;</p><p>자세한 수상팀 명단은 첨부파일에서 확인 가능합니다.</p><p>수상한 모든 팀들 축하드립니다.&nbsp;</p><p><br></p><p><br></p><p><strong>[상금]</strong></p><p>수상팀 제출 서류는 아래 설문지를 통해 팀대표 1명만 제출바랍니다.(팀장이 대표로 수령)</p><p>서류 제출 마감일은<strong> 10월 19일(수)</strong>입니다. 빠른 상금 수령을 위해 마감 기한을 꼭 지켜주세요!!</p><p><strong><a href=\"https://docs.google.com/forms/d/e/1FAIpQLSf0yHqTeIrz82i9-JbEuqeUMDoIA6aI_y6YiZyaDhHh-Fw1DA/viewform?usp=sf_link\">설문지로 바로이동</a></strong></p><p><br></p><p><br></p><p><strong>[상장]</strong></p><p><strong>배부기간: 10.20(목) ~ 21(금)</strong>. 팀별 1부 발급</p><p>배&nbsp; 부&nbsp; 처: SW중심대학지원사업단, 뉴턴홀 218호</p><p>&nbsp; &nbsp;* 상장 수령 시 개인정보 이용 동의서 서명도 함께 해주세요.</p><p>&nbsp;</p><p>&nbsp;</p><p>문의 : SW중심대학 지원사업단 이윤정(260-1478 / lyj1515@handong.edu)</p>")
                .viewCnt(0)
                .importance(false)
                .pubDate(LocalDate.of(2022, Month.JULY, 7))
                .expDate(LocalDate.of(2022, Month.JULY, 12))
                .build());
        noticeRepository.save(Notice.builder()
                .manager(l.get(1))
                .title("[전산전자공학부공학부] 2022 CSEE Lab Week에 초대합니다.")
                .content("<p>안녕하세요?<br /></p><p>전산전자공학부공학부, 일반대학원 전산전자공학부공학과가 <strong>9주차 10/24(월)~10/28(금)</strong>에 각 연구실에 대해 알아가고 다양한 연구의 내용을 공유하는 시간을 가지려고 합니다.&nbsp;</p><p><br></p><p><br></p><p>저희 전산전자공학부공학부, 일반대학원 전산전자공학부공학과에서 어떤 주제의 연구들을 진행하고 있는지 <strong>Lab설명회, 포스터세션</strong>을 통해 이야기를 듣고 싶은 학생들의 많은 참여 바랍니다.&nbsp;</p><p><br></p><p><br></p><p>행사에 참석하면 입시관련된 정보 또한 얻을 수 있으니 AI를 포함한 ICT 관련 분야 진학에 관심있는 모든 학부생들의 참여를 환영합니다.&nbsp; </p><p><img width='70%' height='auto' style='display: block; margin: 0 auto' src='https://user-images.githubusercontent.com/79990740/203981880-4a884a3d-bb42-4f1f-b8ff-74ac7de45045.png' contenteditable=\"false\"><br></p><p><br></p><p><img src=\"https://fonts.gstatic.com/s/e/notoemoji/14.0/2728/32.png\" alt=\"d24;\" contenteditable=\"false\"><strong>온라인 Lab 멘토링 실시 (기간: 10/24(월)~10/28(금))</strong></p><p>(아래 링크접속 후 각 Lab의 오픈카톡방을 통한 자유로운 상담 가능)</p><p><br></p><p><a href=\"https://docs.google.com/document/d/1vPNIac3vfuBxzr3-IsqVqEAFwJtA-6dXiE_RvKhXJHo/edit#\">https://docs.google.com/document/d/1vPNIac3vfuBxzr3-IsqVqEAFwJtA-6dXiE_RvKhXJHo/edit#</a></p><p>&nbsp;</p><p>&nbsp;</p><p><img src=\"https://fonts.gstatic.com/s/e/notoemoji/14.0/2728/32.png\" alt=\"d24;\" contenteditable=\"false\"><strong> Lab토크쇼(10/27(목) 18시30분~) 사전질문 작성</strong></p><p>제출된 질문은 Lab 토크쇼에서 활용되며, 추첨을 통하여 커피쿠폰을 드립니다.</p><p><a href=\"https://forms.gle/a8XWyqXcTec8vdwq5\">https://forms.gle/a8XWyqXcTec8vdwq5</a></p><p>&nbsp;</p><p>&nbsp;</p><p><img src=\"https://fonts.gstatic.com/s/e/notoemoji/14.0/2728/32.png\" alt=\"d24;\" contenteditable=\"false\"> <strong>CSEE Lab Week 행사 참석 희망자(만찬 참여 포함)는 아래 설문지 필수 작성</strong></p><p><br></p><p><a href=\"https://docs.google.com/forms/d/1wkUWbYgcPFFTfrI-M3aOonQYRd_Oq6VHugJx6rVkuRE/edit?pli=1\">https://docs.google.com/forms/d/1wkUWbYgcPFFTfrI-M3aOonQYRd_Oq6VHugJx6rVkuRE/edit?pli=1</a></p><p><br></p><p><br></p><p><strong>**10/28(금)** 포스터세션이 종료되고 만찬(오후 5시 예정)이 있을 예정입니다.</strong></p><p><strong>만찬은 인원수 제한으로 선착순으로 신청을 받습니다.</strong></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><img src=\"https://fonts.gstatic.com/s/e/notoemoji/14.0/1f381/32.png\" alt=\"\uD83C\uDF81\" contenteditable=\"false\"><strong>event</strong><img src=\"https://fonts.gstatic.com/s/e/notoemoji/14.0/1f381/32.png\" alt=\"\uD83C\uDF81\" contenteditable=\"false\"> <strong>CSEE Lab Week 참가자 커피쿠폰 증정</strong>&nbsp;</p><ul><li><p>미션1: 핸드아웃에 제시된 목, 금 행사 중 3개 이상 참석 후 스탬프 받기</p></li><li><p>미션2: 행사 완료 후 CSEE Lab Week 설문조사 작성</p></li><li><p>쿠폰수령: 10주차 학과사무실에 방문하여 수령 (선착순)</p></li><li><p><a href=\"https://docs.google.com/forms/d/10L7pp1SXu6tSmY2IaXT-8GskikhPtEeIYPFQ0Em7RT8/edit#responses\">https://docs.google.com/forms/d/10L7pp1SXu6tSmY2IaXT-8GskikhPtEeIYPFQ0Em7RT8/edit#responses</a></p></li></ul><p>&nbsp;</p><p>&nbsp;</p><p><strong>* 문의: 전산전자공학부공학과 고라경 선생님 (전화: 054-260-3150)</strong></p>")
                .viewCnt(0)
                .importance(false)
                .pubDate(LocalDate.of(2022, Month.AUGUST, 5))
                .expDate(LocalDate.of(2022, Month.AUGUST, 25))
                .build());
        noticeRepository.save(Notice.builder()
                .manager(l.get(0))
                .title("2022-2 비교과 졸업요건 서류 제출 안내(~12/16)")
                .content("<p>안녕하세요?</p><p>17학번이후인 1전공 컴퓨터공학/AI·컴퓨터공학심화 학생 중 캡스톤디자인 교과목 수강생 및 2023.2월 졸업예정자에게 안내드립니다.</p><p>비교과 졸업요건 만족 여부 확인을 위해 첨부 된 양식 작성하여 <strong>구글폼으로 제출해주시기 바랍니다.</strong></p><p>&nbsp; &nbsp;* 비교과 졸업요건은 히즈넷-학생 졸업심사화면의 '<strong>학부추가졸업요건</strong>'에 해당합니다.</p><p>&nbsp; &nbsp;* 해당 학생들에게는 문자로 안내하였습니다.</p><table><thead><tr><th><p>&lt;17학번부터 적용되는&nbsp;비교과&nbsp;졸업요건&gt;</p><p>-&nbsp;산학프로젝트&nbsp;1개 이상 수행&nbsp;(산학R&amp;D프로젝트,&nbsp;또는 산학연계 교과목프로젝트&nbsp;(예:&nbsp;캡스톤디자인))</p><p>-&nbsp;캡스톤프로젝트 결과물 등을 특허,&nbsp;논문, SW시제품 중 한 가지 형태로&nbsp;1건 이상 제출하여야 함&nbsp;(공동 출원자/저자 모두 인정).</p><p>-&nbsp;비교과&nbsp;졸업요건은 캡스톤디자인 과목을 산학 주제로 수행한 결과를&nbsp;논문 발표,&nbsp;특허출원,&nbsp;또는&nbsp;SW등록함으로써 자연스럽게 충족 가능함.</p></th></tr></thead><tbody><tr></tr></tbody></table><p>1. 제출기한: <strong>16주차 금요일(12/16)까지</strong></p><p>2. 제출방법: 구글폼(<a href=\"https://forms.gle/Q7X1VfJL176h2TJ38\">https://forms.gle/Q7X1VfJL176h2TJ38</a>) 제출</p><p>&nbsp; - <strong>23.2월에 졸업하는 경우,</strong> 12/16(금)까지 제출하셔야 졸업 가능합니다.(다음학기 ‘학사학위취득유예’ 학생 포함)</p><p>&nbsp; - <strong>23.2월에 졸업하지 않는 경우,</strong> 다음 학기에 제출하실 수 있습니다.(구글폼에 '졸업안함' 제출)</p><p>3. 참고사항</p><p>&nbsp; 가. 이전에 증빙서류로 제출하였던 사례</p><p>&nbsp; &nbsp; 1) <strong>논문</strong>: 정식 게재된 목차 및 논문 스캔본, 발표 포스터 스캔본</p><p>&nbsp; &nbsp; 2) <strong>특허</strong>: 특허청의 공식 문서로 출원번호 또는 등록번호와 발명자 정보(주소지 포함)가 기재되어 있는 서류</p><p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;(특허출원서, 특허증, 공개특허공보, 등록특허공보 중 1개 선택)</p><p>&nbsp; &nbsp; 3) <strong>시제품제작</strong> (아래 3개 항목 중 1가지 선택)</p><p>&nbsp; &nbsp; &nbsp; 가) SW등록: SW등록증, 프로그램등록신청명세서, 저작권 등록증 등</p><p>&nbsp; &nbsp; &nbsp; 나) 앱 출시:&nbsp;출시 된 앱의 스토어 게시 화면 캡쳐 이미지,&nbsp;앱 출시 확인서 사본 등</p><p>&nbsp; &nbsp; &nbsp; 다) 상용서비스 수준 확인 : 산학프로젝트 참여 기업체의 담당자 또는 기업체 멘토가 작성한 시제품 확인서(첨부 양식)</p><p>&nbsp; 나. 자주묻는질문</p><p>&nbsp; &nbsp; 1) 비교과 졸업요건을 충족하기 위한 결과물은 꼭 캡스톤디자인의 결과물이어야 하나요?</p><p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; - 반드시 캡스톤 결과물이어야 할 필요는 없습니다.</p><p>&nbsp; &nbsp; 2) 저작권 등록을 한 학생이 대표로 하게되어 등록증에 한명의 이름만 기재되는데 어떻게 하나요?</p><p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; - 등록증에 들어있는 제목과 캡스톤 제목이 일치하면 같은 팀원에게 동일하게 적용됩니다.&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;다만, 서류는 개인별로 제출하셔야 합니다.</p><p><br></p><p><br></p><p>문의) csee@handong.edu</p>")
                .viewCnt(0)
                .importance(true)
                .pubDate(LocalDate.of(2022, Month.DECEMBER, 8))
                .expDate(LocalDate.of(2023, Month.DECEMBER, 16))
                .build());
        noticeRepository.save(Notice.builder()
                .manager(l.get(0))
                .title("[이랜드이노플] 채용설명회 안내 및 수요조사")
                .content("<p>안녕하세요:) 반갑습니다.</p><p>이랜드이노플 인사팀입니다.</p><p><br></p><p><br></p><p>금번 [이랜드이노플X한동대학교] 채용설명회에 참여해주시는 분들께 감사의 의미로 간식 및 기념품을 제공해드리고자 수요조사를 진행하게 되었습니다. 일정은 아래와 같이 진행되며, 참석을 원하시는 분들께서는 본 설문에 응답해주시기를 부탁드립니다:)</p><p>&nbsp;</p><p>1. 채용설명회</p><p>&nbsp; 가. 일시: 2022.11.16(수) 12:00~12:50</p><p>&nbsp; 나. 장소: 뉴턴홀 310호</p><p>&nbsp; 다. <strong>참석 혜택</strong></p><p>&nbsp; &nbsp; - 채용설명회에 발걸음 해주시는 학생분들께 아래와 같은 혜택을 드리고자 합니다.</p><p>&nbsp; &nbsp; &nbsp; 1) 서류전형 시 가산점 부여</p><p>&nbsp; &nbsp; &nbsp; 2) 기념품 제공</p><p>&nbsp; &nbsp; &nbsp; 3) Q&amp;A 시간에 학생분들이 궁금해하시는 포인트들을 최대한 답변해드리고자 합니다.</p><p>&nbsp; &nbsp; + <strong>(한동대 Only) 간식 제공</strong>&gt; [한동대] 이랜드이노플 채용설명회 참여 신청서:&nbsp;<strong><a href=\"https://forms.gle/s9B5JbDPsviRBEoq5\">https://forms.gle/s9B5JbDPsviRBEoq5</a></strong></p><p>&nbsp;</p><p>2. 채용 안내</p><p>&nbsp; 가. 채용일정: 2022.11.4(금)~11.21(월)</p><p>&nbsp; 나. 이랜드이노플 채용 관련 링크</p><p>&nbsp; &nbsp; - 채용사이트:&nbsp;<a href=\"https://careers.eland.co.kr/announce/recruit/hxKeGGXNip4urmc1ZmHDz7PJVJ2-\">https://careers.eland.co.kr/announce/recruit/hxKeGGXNip4urmc1ZmHDz7PJVJ2-</a></p><p>&nbsp; &nbsp; - Notion 이노플 공채 블로그:&nbsp;<a href=\"https://elandinnople.oopy.io/38d9b929-de4f-46eb-8234-d5b231973e74\">https://elandinnople.oopy.io/38d9b929-de4f-46eb-8234-d5b231973e74</a></p><p>&nbsp; &nbsp; - 네이버블로그:&nbsp;<a href=\"https://blog.naver.com/elandinnople\">https://blog.naver.com/elandinnople</a></p><p>&nbsp;<img width='70%' height='auto' style='display: block; margin: 0 auto' src=\"https://hisnet.handong.edu/upload/report/cec3d1270979ca6cf908572481f15529%255B%25EC%259D%25B4%25EB%2585%25B8%25ED%2594%258C%255D%2B22%25EB%2585%2584%2B%25ED%2595%2598%25EB%25B0%2598%25EA%25B8%25B0%2B%25EC%258B%25A0%25EC%259E%2585%2B%25EA%25B3%25B5%25EA%25B0%259C%25EC%25B1%2584%25EC%259A%25A9%2B%25ED%258F%25AC%25EC%258A%25A4%25ED%2584%25B0_%25EC%25B5%259C%25EC%25A2%2585%2528%25EA%25B3%25B5%25EC%259C%25A0%25EC%259A%25A9%2529.png\" contenteditable=\"false\"><br></p>")
                .viewCnt(0)
                .importance(false)
                .pubDate(LocalDate.of(2023, Month.MARCH, 6))
                .expDate(LocalDate.of(2023, Month.SEPTEMBER, 26))
                .build());
        noticeRepository.save(Notice.builder()
                .manager(l.get(1))
                .title("[대학원] 세미나(11월 11일, 손현우 교수)-아날로그 연산기 기반의 학습가능한 신경망 시스템")
                .content("<p>한동대학교&nbsp;BK21&nbsp;인공지능 사업단</p><p>2022학년도 2학기 참여대학원생 등록서류 제출 및 연구장학금 신청 안내</p><p>&nbsp;</p><p>&nbsp;</p><p>한동대학교 전산전자공학과&nbsp;4단계&nbsp;BK21 ‘산업혁신을 위한&nbsp;AI&nbsp;고급 인재교육연구단’ (BK21&nbsp;인공지능사업단)에서 다음과 같이 등록 서류를 제출하여주시기 바랍니다.</p><p>&nbsp;</p><p><br></p><p>1. 4단계&nbsp;BK21&nbsp;참여대학원생 기준 (다음의 조건을 모두 충족한 자)</p><p>BK21&nbsp;참여교수의 지도학생 중 전일제(4대보험에 가입되어 있지 않은 학생)로 등록(연구생 등록 포함)한 석·박사과정 대학원생으로 다음 각 호에 해당하는 자</p><p>-입학한 지&nbsp;2년이 지나지 않은 석사과정생</p><p>-입학한 지&nbsp;4년이 지나지 않은 박사과정생 및 박사 수료생</p><p>-입학한 지&nbsp;6년이 지나지 않은 석박통합과정생 및 석박통합과정 수료생</p><p>(위의 기간 기산 시 휴학 및 군복무 기간은 제외함).</p><p>&nbsp;</p><p>위의 자격을 충족한 경우&nbsp;‘참여대학원생’으로 등록되어&nbsp;‘지원대학원생’&nbsp;선발 기회 등&nbsp;BK21&nbsp;사업의 지원을 받을 수 있음.</p><p>&nbsp;</p><p>2.&nbsp;지원대학원생 선발 및 연구장학금 지급</p><p>1)&nbsp;참여대학원생으로 등록된 학생 중&nbsp;70%&nbsp;이내의 대학원생을&nbsp;‘지원대학원생’으로 선발해 연구장학금을 지급함.</p><p>2)&nbsp;지원기간: 2022.09.01.~2023.02.28. (6개월 단위로 선발)</p><p>3)&nbsp;선발기준:&nbsp;<strong>지도교수 추천을 받은 참여대학원생&nbsp;</strong>중 연구/프로젝트 성과 평가에 의해 선발</p><p>4)&nbsp;역할 및 임무</p><p>- BK21&nbsp;참여교수 연구실에서 논문 연구 및 산학 프로젝트를 성실히 수행&nbsp;(주&nbsp;40시간 이상)</p><p>-&nbsp;사업단이 주관하는 각종 세미나,&nbsp;포럼 등의 학술 활동에 참여</p><p>-&nbsp;사업단이 요구하는 성과 자료 보고</p><p>&nbsp;</p><p><br></p><p>3.&nbsp;참여대학원생 등록서류 제출안내(등록서류는 단순참여, 장학금 지원 받는 학생 모두 제출해주세요.)</p><p>1)&nbsp;첨부된 제출서류 다운 및 작성 후&nbsp;BK21&nbsp;사업단 사무실로 서류 제출.</p><p>2)&nbsp;지도교수로부터 지원대학원생(장학금 받는 학생)으로 추천받은 대학원생은&nbsp;<strong>5.제출서류</strong>&nbsp;함께 제출</p><p>&nbsp;</p><p>4.&nbsp;서류 제출 기간:&nbsp;2022년 8월 29일(월) 13:00 - 9월 7일(수) 17:00 (제출기한 엄수)</p><p><br></p><p><br></p><p><br></p><p>5.&nbsp;제출서류</p><p>-&nbsp;연구자등록번호 발급 방법</p><p>: KRI(한국연구업적통합정보시스템&nbsp;<a target='_blank' href=\"http://www.kri.go.kr%29/\">http://www.kri.go.kr)</a>에 접속 회원가입→연구자등록번호 발급&nbsp;</p><p>&nbsp; (가입 시 소속을 반드시&nbsp;‘한동대학교’로 가입)&nbsp;</p><p><br></p><p><br></p><p>1)&nbsp;재학증명서&nbsp;1부.&nbsp;연구생등록자의 경우 연구생등록증&nbsp;1부(2022년 9월 1일자 기준으로 제출)</p><p>2)&nbsp;개인정보 수집 이용 제공 동의서&nbsp;1부. (BK사업에 처음 참여하는 학생만 제출)</p><p>3)&nbsp;참여인력 서약서&nbsp;1부.&nbsp;</p><p>4)&nbsp;참여대학원생 확약서&nbsp;1부.</p><p>5) BK21&nbsp;연구장학금 신청서&nbsp;1부. (지원대학원생-BK장학금 받는 학생만 제출)</p><p>6) 전산전자공학과 학기 보고서&nbsp;1부.</p><p>7)&nbsp;재학증명서&nbsp;1부.(9월 1일 기준)</p><p>&nbsp;</p><p><strong>8) 연구재단 필수 제출 서류&nbsp;'4대 보험 가입 증명서'&nbsp;1부.&nbsp;(22.10.1일 기준일로하여 메일로 회신)</strong></p><p><strong>(**10월1일 기준으로 출력&nbsp;후 제출 바랍니다. 9월에는 제출하지 않아도 됩니다!)</strong></p><p>-&nbsp;출력 방법: 4대 사회보험 포털서비스(<a href=\"http://www.4insure.or.kr%29/\">http://www.4insure.or.kr)</a> 로그인(회원가입,&nbsp;공인인증서 필요)&nbsp;→&nbsp;상단 메뉴 증명서발급(가입내역확인)&nbsp;클릭 후 출력</p><p>&nbsp;</p><p>9)&nbsp;대학원 성적표&nbsp;1부. (신입생은 제출X)</p><p>&nbsp;</p><p>&lt;한국연구재단 필수 수강!!-참여기간 중 1회 수강 후 수료증 제출해야함, 교육 미이수자 및&nbsp; 22-2학기 신입생만 수강하면 됩니다.&gt;</p><p>10)&nbsp;온라인 교육 수료증&nbsp;(<a href=\"http://alpha-campus.kr/)\">http://alpha-campus.kr/)</a>&nbsp;에 접속하여 로그인 후 상단 메뉴의 '탐색'-&gt;'온라인교육' -&gt; '건강한 연구환경 조성을 위한 인권침해예방교육'클릭 -&gt;'수강신청' 클릭 후 팝업창에서 수강신청 정보 확인 및 '신청완료'-&gt; '학습'-&gt; '학습중과정'-&gt;&nbsp;'건강한 연구환경 조성을 위한 인권침해예방교육' 순서대로 클릭 후 '학습하기'</p><p>컨텐츠 수강 후&nbsp;‘MY'-&gt; '학습이력'클릭 후 수료증 발급 및 보관)&nbsp;</p><p>*수료증은 메일로 회신부탁합니다.&nbsp;</p><p>&nbsp;</p><p>11)&nbsp;통장사본 및 신분증 사본 각&nbsp;1부.</p><p>&nbsp;</p><p>※&nbsp;외국인 참여대학원생의 경우 외국인 등록증 앞,&nbsp;뒤 사본(D-2비자 여부 및 만료기한 확인)&nbsp;제출.</p><p>※ 기존&nbsp;참여대학원생이 지원할 경우: 2)개인정보 수집 이용 제공 동의서,&nbsp;10)온라인 교육 수료증 제출 생략.</p><p>&nbsp;</p><p>6.&nbsp;서류 제출 및 문의처</p><p>1) BK21사업단 사무실:&nbsp;뉴턴홀&nbsp;309호 전산전자공학부 사무실&nbsp;담당자 고라경(Tel: 260-3150)</p><p>2)&nbsp;신청 시 모든 서류는 위 제출서류 순서대로 정리하여 원본 제출 바랍니다.</p><p>&nbsp;</p><p>7.&nbsp;유의사항:&nbsp;제출된 서류는 반환하지 않으며,&nbsp;허위사실 기재 시 선발을 취소함.</p>")
                .viewCnt(0)
                .importance(false)
                .pubDate(LocalDate.of(2023, Month.FEBRUARY, 9))
                .expDate(LocalDate.of(2023, Month.SEPTEMBER, 15))
                .build());
        noticeRepository.save(Notice.builder()
                .manager(l.get(2))
                .title("[SW중심대] 11월 2일(수) 류석문 쏘카 CTO 특강 안내")
                .content("<p><strong>[SW중심대] 11월 2일(수) 류석문&nbsp; 쏘카 CTO 특강 안내</strong></p><p><br></p><p><br></p><p><strong><img width='70%' height='auto' style='display: block; margin: 0 auto' src=\"https://hisnet.handong.edu/upload/report/5e67fa90c7140644945005974991484fSW%25EC%25A4%2591%25EC%258B%25AC%25EB%258C%2580%2B2022-2%25ED%2595%2599%25EA%25B8%25B0%2BCSEE%2B%25ED%258F%25AC%25EC%258A%25A4%25ED%2584%25B0_re.jpg\" contenteditable=\"false\"></strong><br></p><p><br></p><p><br></p><p>&nbsp;</p><p>1. 강사 :&nbsp;<strong>류석문 쏘카 CTO</strong></p><p><br></p><p>2. 주제 :&nbsp;&nbsp;프로그래머로 산다는 것</p><p>&nbsp;</p><p>3. 일시 : 2022년 11월 2일 (수) 오후 1시&nbsp;</p><p>&nbsp;</p><p>4. 장소 :&nbsp;오석관 405호, 401호,</p><p>&nbsp;</p><p>5. 소감문 제출 방법 : 링크 접속 후 제출&nbsp;(<a href=\"https://forms.gle/gRtNgqWtFnxYmMXVA\">https://forms.gle/gRtNgqWtFnxYmMXVA</a>)</p><p><br></p><p>&nbsp; &nbsp;&nbsp;* 공프기 및 캡스톤 수강생 외 학생들은 강의 시청 후 반드시 소감문을 제출하셔야 합니다.&nbsp;(11. 4(금)&nbsp;밤12시까지)</p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p>&nbsp; &nbsp;☎ 문의 : SW중심대학 지원사업단 김선영 (T.260-1492, E-Mail : pooh8276<a href=\"mailto:cylim@handong.edu\">@handong.edu</a>)</p>")
                .viewCnt(0)
                .importance(true)
                .pubDate(LocalDate.of(2022, Month.OCTOBER, 7))
                .expDate(LocalDate.of(2022, Month.OCTOBER, 28))
                .build());

        noticeRepository.save(Notice.builder()
                .manager(l.get(3))
                .title("일본 IT 강독 2022년 2학기 첫번쨰 모임이 이번주 수요일 11월 09일 저녁 8시에 열립니다.")
                .content("<p>&nbsp;일본 IT 강독&nbsp; &nbsp;2022년 2학기 첫번？ 모임이 이번주&nbsp; 수요일 11월 09일 저녁 8시에 열립니다.</p><p><br></p><p><br></p><p>2022년2학기 일본IT학회 주관 \"일본 IT 강독\"</p><p><br></p><p><br></p><p>일본의&nbsp; IT시스템 개발문서, IT 트렌드 관련 일본어 기사 등을 같이 읽어 나가는 시간입니다. 일본은 IT시스템 개발과정에서 Documentation을 아주 철저히 하고 있습니다. 따라서 일본 IT 취업시 일본어를 읽고 쓰는 능력은 회화능력 이상으로 중요합니다. IT관련 일본어 문서를 읽어나가며 일본어를 더 공부하고 싶게 하고자 합니다.</p><p><br></p><p><br></p><p>본 시간은 22년이상 일본에서 IT개발자로서 일본인들과 함께 일해오신 일본 도레이쿠 김종환 대표님이 직접 강의합니다.</p><p><br></p><p><br></p><p>참여자격 : 일본어 초급 수준(한동대학교 일본어&nbsp; I 수강 수준)</p><p><br></p><p><br></p><p>일정</p><p>제 1 차 모임. 11월 09일(수 저녁 8시) 11주차&nbsp;</p><p>제 2 차 모임. 11월 23일(수 저녁 8시) 13주차&nbsp;</p><p>&nbsp;</p><p>줌 접속 주소</p><p>https://handong.zoom.us/j/82808657679</p><p><br></p><p><br></p><p>- 참석자에게는 마일리지가 부여됩니다.</p><p>- 문의 : 김기석 교수(peterkim@handong.edu) ,박훈성(01043417017 / 21800333@handong.ac.kr)&nbsp;</p>")
                .viewCnt(0)
                .importance(true)
                .pubDate(LocalDate.of(2023, Month.JANUARY, 1))
                .expDate(LocalDate.of(2023, Month.JULY, 7))
                .build());
        noticeRepository.save(Notice.builder()
                .manager(l.get(2))
                .title("23-1학기 공학프로젝트기획(공프기) 사전신청 안내")
                .content("<p>2023년 봄학기 공학프로젝트기획(공프기)을 수강하기 원하는 학생은</p><p><strong>11월&nbsp;6일까지</strong>&nbsp;아래 링크의 웹사이트에서 사전 신청을 접수하시기 바랍니다.</p><p><a href=\"http://walab.handong.edu/csc/\">http://walab.handong.edu/csc/</a></p><p>사전 신청은 공학프로젝트기획 주제 제안과 팀 구성 과정을 효율적으로 하기 위한 작업이니,&nbsp;</p><p><strong>수강 의사가 조금이라도 있다면 반드시 사전 신청</strong>을 접수하기 바랍니다.</p><p>사전신청에 접수한 학생에 한하여 관련 공지사항과 공프기 주제 제안 내용을&nbsp;공개할 예정입니다.</p><p><br></p><ul><li><p>2023-1학기 공프기를 위한 일정 계획</p><ul><li><p>2022-2학기 9주차</p><ul><li><p>CSEE Lab week (연구실 소개)</p></li></ul></li><li><p>2022-2학기 10주차</p><ul><li><p><strong>2023-1학기를 위한 사전신청</strong></p></li><li><p>수강 예정 학생 수 파악 및 예상 프로젝트 팀 수 확정</p></li></ul></li><li><p>2022-2학기 12주차 ~</p><ul><li><p>주제 공개</p></li><li><p>관심이 있는 주제/교수님께 개별 연락하여 팀 구성</p></li></ul></li></ul></li></ul>")
                .viewCnt(0)
                .importance(true)
                .pubDate(LocalDate.of(2022, Month.OCTOBER, 25))
                .expDate(LocalDate.of(2022, Month.OCTOBER, 31))
                .build());
        noticeRepository.save(Notice.builder()
                .manager(l.get(3))
                .title(" 2022 한동대 C프로그래밍 컨테스트 안내")
                .content("<ul><li><p>일시 : 2022년 12월 9일 15주차 금요일 오후 8시~10시(120분)</p></li><li><p>대상 : 전교생 중 1학년 또는 SW관련 전공이 아닌 학생</p></li><li><p>시험방법 : Replit.com 사이트에 온라인 접속 후 문제를 풀어 제출함(시험 중 구글링 또는 본인의 소스 참조 가능, 타인과 대화, 메시징, 이메일 금지)</p></li><li><p>출제범위 : C프로그래밍 수업 전반</p></li><li><p>문제 수 : 10문제 이내</p></li></ul><p><br></p><p><br></p><p>▶ 컨테스트 관련 질문응답 :<a target='_blank' href=\"http://www.classum.com/EXZRQI\"> www.classum.com/EXZRQI</a>&nbsp;</p><p><br></p><p><br></p><p>▶ 시험 서버 접속 및 테스트 문제 풀이 : 2022년 12월 9일(금) 18시까지</p><ul><li><p>Replit.com 링크에 접속한다. (Chrome 사용 권장)</p></li><li><p><a href=\"https://replit.com/teams/join/whodntufxzxetztsmzbadhqtdqrfbzpn-HGUCContest\">https://replit.com/teams/join/whodntufxzxetztsmzbadhqtdqrfbzpn-HGUCContest</a></p></li><li><p>만일 회원가입이 되어 있지 않다면 가입 후 로그인한다.</p></li><li><p>본인 Profile에서 반드시 First name에 자신의 한글이름, Last name에 자신의 학번8자리를 기입할 것. (인적사항 확인불가 시 수상 제외)</p></li><li><p>회원가입 후 이메일주소 인증할 것</p></li><li><p>테스트용 문제를 확인하고, 문제를 해결하는 코드를 작성하여 실행해본다.</p></li><li><p>테스트케이스를 실행해본 후, 제출(submit)한다.</p></li></ul><p><br></p><p><br></p><p>▶ 컨테스트 참여 시 주의사항</p><ul><li><p>시험 시작 전에 Replit.com 링크에 접속한다. (Chrome 사용 권장)</p></li><li><p><a href=\"https://replit.com/teams/join/whodntufxzxetztsmzbadhqtdqrfbzpn-HGUCContest\">https://replit.com/teams/join/whodntufxzxetztsmzbadhqtdqrfbzpn-HGUCContest</a></p></li><li><p>시작시간이 되면 문제가 공개됩니다. (새로고침 필수)</p></li><li><p>제한 시간 내에 공개된 문제를 최대한 해결하여 테스트케이스를 실행한 후, 제출(submit)한다. 반드시 순서대로 문제를 풀지 않아도 됨</p></li><li><p>다음의 경우에는 제출 무효로 처리됨</p><ul><li><p>Due date가 지난 후, 제출하는 경우</p></li><li><p>Due date가 지난 후, 문제를 다시 열어 보는 경우</p></li><li><p>Due date가 지난 후, 자신의 코드를 수정한 경우</p></li><li><p>동일계정으로 동시에 2개 이상의 Replit.com 접속한 경우</p></li></ul></li></ul><p>▶ 채점 기준</p><ul><li><p>기준 1 : 성공적으로 해결하여 테스트케이스 모두 통과한 후, 제출한 문제 갯수가 많은 순</p></li><li><p>기준 2 : 해결한 모든 문제의 평균제출시간이 빠른 순</p></li><li><p>기준 3 : 미완성 제출 경우 통과된 테스트 케이스가 많은 순</p></li></ul><p><br></p><p><br></p><p>▶ 참가자 혜택</p><ul><li><p>1문제 이상을 성공 제출한 모든 참가자에게 소중대 마일리지 적립</p></li><li><p>우수자에게는 상금 지급 (1학년 또는 SW전공이력이 없는 학생만 수상)</p><ul><li><p>총상금 200만원</p></li><li><p>대상 1명 : 20만원</p></li><li><p>최우수상 5명 내외 : 각 15만원</p></li><li><p>우수상 10명 내외 : 각 10만원</p></li><li><p>장려상 : 각 5만원</p></li></ul></li><li><p>수상 규모는 변동될 수 있음</p></li></ul><p><br></p><p><br></p>")
                .viewCnt(0)
                .importance(true)
                .pubDate(LocalDate.of(2022, Month.NOVEMBER, 2))
                .expDate(LocalDate.of(2023, Month.NOVEMBER, 9))
                .build());
        noticeRepository.save(Notice.builder()
                .manager(l.get(2))
                .title("[지역선도대학] 2022 대경권 대학생 AI프로그래밍 경진대회 개최")
                .content("<p><strong>2022 대경권 대학생 AI프로그래밍 경진대회 개최&nbsp;</strong></p><p><br></p><p><br></p><p><img width='70%' height='auto' style='display: block; margin: 0 auto' src=\"https://hisnet.handong.edu/upload/report/43da5b87ab29bea96a127b999323a654AI%25ED%2594%2584%25EB%25A1%259C%25EA%25B7%25B8%25EB%259E%2598%25EB%25B0%258D%2B%25EA%25B2%25BD%25EC%25A7%2584%25EB%258C%2580%25ED%259A%258C.png\" contenteditable=\"false\"><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p>지역선도대학육성사업에서는 재학생들의 인공지능(AI) 컴퓨팅 사고역량, 프로그래밍 역량 문제해결 역량 강화 등을 위하여 2022 대경권 대학생 AI프로그래밍 경진대회를 개최합니다.</p><p><br></p><p><br></p><p><br></p><p><br></p><p>■ 접수기간: 2022. 10. 26.(수) ~ 11. 9.(수) 까지&nbsp;</p><p>■ 접수방법: 대회 홈페이지에서 직접 신청(참가비 무료)</p><p><br></p><p><br></p><p>대회 홈페이지 주소 및 자세한 내용은 지역선도대학육성사업 홈페이지 → 온라인신청 → 진행프로그램에서 가능</p><p>(https://spoke.knu.ac.kr/03_sub/01_sub.html)</p><p><br></p><p><br></p><p>■ 문의 : 안영은 연구원(054-260-3124, aye3384@handong.edu)</p>")
                .viewCnt(0)
                .importance(false)
                .pubDate(LocalDate.of(2023, Month.OCTOBER, 21))
                .expDate(LocalDate.of(2023, Month.OCTOBER, 27))
                .build());
        noticeRepository.save(Notice.builder()
                .manager(l.get(0))
                .title("[CSEE Lab Week] Lab 토크쇼 관련 사전 질문")
                .content("<p>안녕하세요. 컴퓨터 공학 전공 주임 안민규 교수입니다.</p><p>&nbsp;</p><p>이전 글에 공지된 것처럼 다음 주 9주차에는 전산전자공학부공학부/전산전자공학부공학과가 CSEE Lab Week가 진행됩니다.</p><p>관련 글&gt;&nbsp;https://hisnet.handong.edu/myboard/read.php?id=35135&amp;Board=B0029</p><p>&nbsp;</p><p>연구실에 관심이 있거나, 2023-1학기 공학프로젝트 기획 수강을 계획하는 학생들에게는 큰 도움이 될 것 같습니다.</p><p>&nbsp;</p><p>&nbsp;</p><p>각 연구실에 대하여 궁금한 점은 9주차 동안 진행되는 Lab 멘토링을 통해 문의하고 답변을 받기 바랍니다.</p><p>또한, 목요일 18:30에는 CSEE 연구실, 연구실 생활, 졸업 프로젝트, 진로 등에 관하여 이야기를 교수님들과 나눌 수 있는 자리를 마련하였으니 많은 학생들이 참여하여 정보를 얻는 시간이 되길 빕니다.</p><p>&nbsp;</p><p>&nbsp;</p><p>관련하여 현재 사전 질문을 받고 있습니다. 아래의 링크를 통하여 묻고 싶은 사항을 남겨주시기 바랍니다.</p><p>&nbsp;</p><p>&nbsp;</p><p><img width='70%' height='auto' style='display: block; margin: 0 auto' src=\"https://fonts.gstatic.com/s/e/notoemoji/14.0/2728/32.png\" alt=\"d24;\" contenteditable=\"false\"><strong> Lab토크쇼(10/27(목) 18시30분~) 사전질문 작성</strong></p><p>제출된 질문은 Lab 토크쇼에서 활용되며, 추첨을 통하여 커피쿠폰을 드립니다.</p><p><a href=\"https://forms.gle/a8XWyqXcTec8vdwq5\">https://forms.gle/a8XWyqXcTec8vdwq5</a></p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>안민규 교수.</p>")
                .viewCnt(0)
                .importance(false)
                .pubDate(LocalDate.of(2022, Month.NOVEMBER, 2))
                .expDate(LocalDate.of(2023, Month.NOVEMBER, 9))
                .build());
    }


    private void saveStudent() {

        studentRepository.save(Student.builder()
                .name("박성진")
                .department(departementRepository.findByName("전산전자공학부"))
                .studentNum("21700266")
                .semester(8)
                .major1(majorRepository.findByName("컴퓨터공학심화전공"))
                .major2(majorRepository.findByName("-"))
                .phone("010-9484-4321")
                .email("david@handong.ac.kr")
                .profile("https://user-images.githubusercontent.com/63008958/203534676-49fb985a-c686-4965-9dc4-d46ae90cc3e7.png")
                .blog("blog.com")
                .githubId("@davidpiao.github")
                .loginCnt(0L)
                .readme("david's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("안병웅")
                .department(departementRepository.findByName("생명과학부"))
                .studentNum("21600000")
                .semester(6)
                .major1(majorRepository.findByName("생명과학전공"))
                .major2(majorRepository.findByName("-"))
                .phone("010-1623-1512")
                .email("mh03@handong.ac.kr")
                .profile("https://user-images.githubusercontent.com/63008958/203915182-c8216b87-9e06-4a10-8efe-b8d0cb43b5af.png")
                .blog("blog.com")
                .githubId("@wooong.github")
                .loginCnt(0L)
                .readme("an's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("홍성헌")
                .department(departementRepository.findByName("커뮤니케이션학부"))
                .studentNum("21800929")
                .semester(8)
                .major1(majorRepository.findByName("공연영상학전공"))
                .major2(majorRepository.findByName("언론정보학전공"))
                .phone("010-1623-3322")
                .email("hong@handong.ac.kr")
                .profile("https://user-images.githubusercontent.com/63008958/203469086-7e27bf70-cca1-4bf2-be74-e16dbcee7507.jpeg")
                .blog("blog.com")
                .githubId("@hong.github")
                .loginCnt(0L)
                .readme("hong's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("이인혁")
                .department(departementRepository.findByName("기계제어공학부"))
                .studentNum("21700032")
                .semester(5)
                .major1(majorRepository.findByName("전자제어공학전공"))
                .major2(majorRepository.findByName("기계공학전공"))
                .phone("010-4983-6555")
                .email("lee@handong.ac.kr")
                .profile("https://user-images.githubusercontent.com/63008958/203468795-8bf6ee4b-5ee6-4ae4-87ed-ca438307c30f.jpeg")
                .blog("blog.com")
                .githubId("@ee.github")
                .loginCnt(0L)
                .readme("lee's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("정석민")
                .department(departementRepository.findByName("콘텐츠융합디자인학부"))
                .studentNum("22000432")
                .semester(3)
                .major1(majorRepository.findByName("시각디자인전공"))
                .major2(majorRepository.findByName("-"))
                .phone("010-4983-6555")
                .email("jeong@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
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
                .profile("https://user-images.githubusercontent.com/63008958/203534764-4f30254d-d917-444c-84f9-d8e922b967fa.png")
                .blog("blog.com")
                .githubId("@song.github")
                .loginCnt(0L)
                .readme("song's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("문하현")
                .department(departementRepository.findByName("전산전자공학부"))
                .studentNum("22200000")
                .semester(5)
                .major1(majorRepository.findByName("컴퓨터공학전공"))
                .major2(majorRepository.findByName("생명과학전공"))
                .phone("010-1234-1234")
                .email("kim@handong.ac.kr")
                .profile("https://user-images.githubusercontent.com/63008958/203492174-ef61e68c-a3d9-429e-8947-75542423977a.png")
                .blog("blog.com")
                .githubId("@kim.github")
                .loginCnt(0L)
                .readme("kim's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("박관희")
                .department(departementRepository.findByName("국제어문학부"))
                .studentNum("22200001")
                .semester(3)
                .major1(majorRepository.findByName("국제지역학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-1234-5678")
                .email("park@handong.ac.kr")
                .profile("https://user-images.githubusercontent.com/63008958/203862630-2915aaad-8ff0-42ae-b217-fcbce19e4791.png")
                .blog("blog.com")
                .githubId("@park.github")
                .loginCnt(0L)
                .readme("park's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("이연진")
                .department(departementRepository.findByName("경영경제학부"))
                .studentNum("22200002")
                .semester(5)
                .major1(majorRepository.findByName("경영학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-5678-1234")
                .email("lee@handong.ac.kr")
                .profile("https://user-images.githubusercontent.com/63008958/203536628-985c37e3-49a9-4a50-82a3-485c09c456c4.png")
                .blog("blog.com")
                .githubId("@lee.github")
                .loginCnt(0L)
                .readme("lee's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("게스트")
                .department(departementRepository.findByName("법학부"))
                .studentNum("22200003")
                .semester(6)
                .major1(majorRepository.findByName("한국법전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-1111-1111")
                .email("jeong@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-10/Original Logo.png")
                .blog("blog.com")
                .githubId("@jeong.github")
                .loginCnt(0L)
                .readme("jeong's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("우한동")
                .department(departementRepository.findByName("커뮤니케이션학부"))
                .studentNum("22200004")
                .semester(6)
                .major1(majorRepository.findByName("공연영상학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-2222-3333")
                .email("woo@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@woo.github")
                .loginCnt(0L)
                .readme("woo's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("위한동")
                .department(departementRepository.findByName("상담심리사회복지학부"))
                .studentNum("22200005")
                .semester(6)
                .major1(majorRepository.findByName("사회복지학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-4444-1234")
                .email("wi@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@wi.github")
                .loginCnt(0L)
                .readme("wi's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("하한동")
                .department(departementRepository.findByName("공간환경시스템공학부"))
                .studentNum("22200006")
                .semester(8)
                .major1(majorRepository.findByName("도시환경공학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-9999-1111")
                .email("ha@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@ha.github")
                .loginCnt(0L)
                .readme("ha's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("마한동")
                .department(departementRepository.findByName("콘텐츠융합디자인학부"))
                .studentNum("22200007")
                .semester(4)
                .major1(majorRepository.findByName("컴퓨터공학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-4312-4312")
                .email("ma@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@ma.github")
                .loginCnt(0L)
                .readme("ma's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("유한동")
                .department(departementRepository.findByName("기계제어공학부"))
                .studentNum("22200008")
                .semester(6)
                .major1(majorRepository.findByName("기계공학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-3434-2323")
                .email("yu@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@yu.github")
                .loginCnt(0L)
                .readme("yu's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("진한동")
                .department(departementRepository.findByName("ICT 창업학부"))
                .studentNum("22200009")
                .semester(7)
                .major1(majorRepository.findByName("ICT 융합전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-8787-8787")
                .email("jin@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@jin.github")
                .loginCnt(0L)
                .readme("jin's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("김건휘")
                .department(departementRepository.findByName("전산전자공학부"))
                .studentNum("21700234")
                .semester(2)
                .major1(majorRepository.findByName("컴퓨터공학전공"))
                .major2(majorRepository.findByName("생명과학전공"))
                .phone("010-1234-1234")
                .email("kim@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@kim.github")
                .loginCnt(0L)
                .readme("kim's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("이도경")
                .department(departementRepository.findByName("국제어문학부"))
                .studentNum("21200012")
                .semester(9)
                .major1(majorRepository.findByName("국제지역학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-1234-5678")
                .email("park@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@park.github")
                .loginCnt(0L)
                .readme("park's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("정민수")
                .department(departementRepository.findByName("경영경제학부"))
                .studentNum("22200032")
                .semester(5)
                .major1(majorRepository.findByName("경영학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-6655-4232")
                .email("lee@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
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
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@jeong.github")
                .loginCnt(0L)
                .readme("jeong's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("김빛나리")
                .department(departementRepository.findByName("커뮤니케이션학부"))
                .studentNum("22200023")
                .semester(3)
                .major1(majorRepository.findByName("공연영상학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-2332-4333")
                .email("woo@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@woo.github")
                .loginCnt(0L)
                .readme("woo's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("김혜린")
                .department(departementRepository.findByName("상담심리사회복지학부"))
                .studentNum("21900021")
                .semester(4)
                .major1(majorRepository.findByName("사회복지학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-4444-1234")
                .email("wi@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@wi.github")
                .loginCnt(0L)
                .readme("wi's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("이소희")
                .department(departementRepository.findByName("공간환경시스템공학부"))
                .studentNum("21800002")
                .semester(9)
                .major1(majorRepository.findByName("도시환경공학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-3221-6665")
                .email("ha@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@ha.github")
                .loginCnt(0L)
                .readme("ha's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("이소연")
                .department(departementRepository.findByName("콘텐츠융합디자인학부"))
                .studentNum("21400025")
                .semester(2)
                .major1(majorRepository.findByName("컴퓨터공학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-4211-1123")
                .email("ma@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@ma.github")
                .loginCnt(0L)
                .readme("ma's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("이동영")
                .department(departementRepository.findByName("기계제어공학부"))
                .studentNum("21100234")
                .semester(10)
                .major1(majorRepository.findByName("기계공학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-2311-9992")
                .email("yu@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@yu.github")
                .loginCnt(0L)
                .readme("yu's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("이연진")
                .department(departementRepository.findByName("ICT 창업학부"))
                .studentNum("21800012")
                .semester(9)
                .major1(majorRepository.findByName("ICT 융합전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-1234-0494")
                .email("jin@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@jin.github")
                .loginCnt(0L)
                .readme("jin's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("김시온")
                .department(departementRepository.findByName("전산전자공학부"))
                .studentNum("22100032")
                .semester(2)
                .major1(majorRepository.findByName("컴퓨터공학전공"))
                .major2(majorRepository.findByName("생명과학전공"))
                .phone("010-3232-6767")
                .email("kim@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@kim.github")
                .loginCnt(0L)
                .readme("kim's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("황유민")
                .department(departementRepository.findByName("국제어문학부"))
                .studentNum("21600432")
                .semester(1)
                .major1(majorRepository.findByName("국제지역학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-4322-9009")
                .email("park@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@park.github")
                .loginCnt(0L)
                .readme("park's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("엄서영")
                .department(departementRepository.findByName("경영경제학부"))
                .studentNum("22000231")
                .semester(5)
                .major1(majorRepository.findByName("경영학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-6653-4231")
                .email("lee@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
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
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@jeong.github")
                .loginCnt(0L)
                .readme("jeong's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("천그루")
                .department(departementRepository.findByName("커뮤니케이션학부"))
                .studentNum("22200026")
                .semester(3)
                .major1(majorRepository.findByName("공연영상학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-2312-4343")
                .email("woo@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@woo.github")
                .loginCnt(0L)
                .readme("woo's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("이하민")
                .department(departementRepository.findByName("상담심리사회복지학부"))
                .studentNum("21910032")
                .semester(4)
                .major1(majorRepository.findByName("사회복지학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-4444-4234")
                .email("wi@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@wi.github")
                .loginCnt(0L)
                .readme("wi's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("박관희")
                .department(departementRepository.findByName("공간환경시스템공학부"))
                .studentNum("21800232")
                .semester(9)
                .major1(majorRepository.findByName("도시환경공학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-3221-6365")
                .email("ha@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@ha.github")
                .loginCnt(0L)
                .readme("ha's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("현요섭")
                .department(departementRepository.findByName("콘텐츠융합디자인학부"))
                .studentNum("21400325")
                .semester(2)
                .major1(majorRepository.findByName("컴퓨터공학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-4211-1673")
                .email("ma@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@ma.github")
                .loginCnt(0L)
                .readme("ma's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("이찬호")
                .department(departementRepository.findByName("기계제어공학부"))
                .studentNum("21100734")
                .semester(10)
                .major1(majorRepository.findByName("기계공학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-2911-9192")
                .email("yu@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@yu.github")
                .loginCnt(0L)
                .readme("yu's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("아무개")
                .department(departementRepository.findByName("ICT 창업학부"))
                .studentNum("22200012")
                .semester(9)
                .major1(majorRepository.findByName("ICT 융합전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-4321-1275")
                .email("jin@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@jin.github")
                .loginCnt(0L)
                .readme("jin's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("김요나")
                .department(departementRepository.findByName("전산전자공학부"))
                .studentNum("21100032")
                .semester(2)
                .major1(majorRepository.findByName("컴퓨터공학전공"))
                .major2(majorRepository.findByName("생명과학전공"))
                .phone("010-6731-1423")
                .email("kim@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@kim.github")
                .loginCnt(0L)
                .readme("kim's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("박도마")
                .department(departementRepository.findByName("국제어문학부"))
                .studentNum("21900432")
                .semester(1)
                .major1(majorRepository.findByName("국제지역학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-4222-3009")
                .email("park@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@park.github")
                .loginCnt(0L)
                .readme("park's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("이배드로")
                .department(departementRepository.findByName("경영경제학부"))
                .studentNum("22000331")
                .semester(5)
                .major1(majorRepository.findByName("경영학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-6653-9991")
                .email("lee@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
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
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@jeong.github")
                .loginCnt(0L)
                .readme("jeong's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("기운찬")
                .department(departementRepository.findByName("커뮤니케이션학부"))
                .studentNum("22100027")
                .semester(3)
                .major1(majorRepository.findByName("공연영상학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-2112-4243")
                .email("woo@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@woo.github")
                .loginCnt(0L)
                .readme("woo's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("곤잘레스")
                .department(departementRepository.findByName("상담심리사회복지학부"))
                .studentNum("21900112")
                .semester(4)
                .major1(majorRepository.findByName("사회복지학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-1144-4214")
                .email("wi@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@wi.github")
                .loginCnt(0L)
                .readme("wi's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("다비드")
                .department(departementRepository.findByName("공간환경시스템공학부"))
                .studentNum("21800332")
                .semester(9)
                .major1(majorRepository.findByName("도시환경공학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-3331-6365")
                .email("ha@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@ha.github")
                .loginCnt(0L)
                .readme("ha's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("도베르만")
                .department(departementRepository.findByName("콘텐츠융합디자인학부"))
                .studentNum("21400425")
                .semester(2)
                .major1(majorRepository.findByName("컴퓨터공학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-4211-2673")
                .email("ma@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@ma.github")
                .loginCnt(0L)
                .readme("ma's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("진시황")
                .department(departementRepository.findByName("기계제어공학부"))
                .studentNum("21100714")
                .semester(10)
                .major1(majorRepository.findByName("기계공학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-2931-9192")
                .email("yu@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@yu.github")
                .loginCnt(0L)
                .readme("yu's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("황유비")
                .department(departementRepository.findByName("ICT 창업학부"))
                .studentNum("21900012")
                .semester(9)
                .major1(majorRepository.findByName("ICT 융합전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-4891-1275")
                .email("jin@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@jin.github")
                .loginCnt(0L)
                .readme("jin's readme")
                .build());

        studentRepository.save(Student.builder()
                .name("우요셉")
                .department(departementRepository.findByName("전산전자공학부"))
                .studentNum("21900032")
                .semester(2)
                .major1(majorRepository.findByName("컴퓨터공학전공"))
                .major2(majorRepository.findByName("생명과학전공"))
                .phone("010-9191-1423")
                .email("kim@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@kim.github")
                .loginCnt(0L)
                .readme("kim's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("홍아담")
                .department(departementRepository.findByName("국제어문학부"))
                .studentNum("21901132")
                .semester(1)
                .major1(majorRepository.findByName("국제지역학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-9991-3009")
                .email("park@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@park.github")
                .loginCnt(0L)
                .readme("park's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("김이브")
                .department(departementRepository.findByName("경영경제학부"))
                .studentNum("22000131")
                .semester(5)
                .major1(majorRepository.findByName("경영학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-6653-9881")
                .email("lee@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
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
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@jeong.github")
                .loginCnt(0L)
                .readme("jeong's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("기윤호")
                .department(departementRepository.findByName("커뮤니케이션학부"))
                .studentNum("22100088")
                .semester(3)
                .major1(majorRepository.findByName("공연영상학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-2188-4243")
                .email("woo@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@woo.github")
                .loginCnt(0L)
                .readme("woo's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("김영찬")
                .department(departementRepository.findByName("상담심리사회복지학부"))
                .studentNum("21900232")
                .semester(4)
                .major1(majorRepository.findByName("사회복지학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-6644-4214")
                .email("wi@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@wi.github")
                .loginCnt(0L)
                .readme("wi's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("김영헌")
                .department(departementRepository.findByName("공간환경시스템공학부"))
                .studentNum("21810232")
                .semester(9)
                .major1(majorRepository.findByName("도시환경공학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-3431-6365")
                .email("ha@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@ha.github")
                .loginCnt(0L)
                .readme("ha's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("김하은")
                .department(departementRepository.findByName("콘텐츠융합디자인학부"))
                .studentNum("21400825")
                .semester(2)
                .major1(majorRepository.findByName("컴퓨터공학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-4111-2673")
                .email("ma@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@ma.github")
                .loginCnt(0L)
                .readme("ma's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("장유진")
                .department(departementRepository.findByName("기계제어공학부"))
                .studentNum("21100114")
                .semester(10)
                .major1(majorRepository.findByName("기계공학전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-2931-9192")
                .email("yu@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
                .blog("blog.com")
                .githubId("@yu.github")
                .loginCnt(0L)
                .readme("yu's readme")
                .build());
        studentRepository.save(Student.builder()
                .name("정수산나")
                .department(departementRepository.findByName("ICT 창업학부"))
                .studentNum("21900312")
                .semester(9)
                .major1(majorRepository.findByName("ICT 융합전공"))
                .major2(majorRepository.findByName("컴퓨터공학전공"))
                .phone("010-4821-1175")
                .email("jin@handong.ac.kr")
                .profile("https://hispath.s3.ap-northeast-2.amazonaws.com/upload/student-5/plant-2004483_1920.jpg")
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
                .name("비교과-학회활동")
                .build());
        categoryRepository.save(Category.builder()
                .name("기타")
                .build());
    }


    private void saveDepartment() {
        departementRepository.save(Department.builder()
                .name("전산전자공학부")
                .profile("https://user-images.githubusercontent.com/63008958/203982136-82098fb9-f082-40e3-a677-e84076ea7745.png")
                .build());
        departementRepository.save(Department.builder()
                .name("국제어문학부")
                .profile("https://user-images.githubusercontent.com/63008958/203982173-f22aa5a5-0e7f-45cc-91b0-1870ced20441.png")
                .build());
        departementRepository.save(Department.builder()
                .name("경영경제학부")
                .profile("https://user-images.githubusercontent.com/63008958/203982171-65bbc7d2-a8de-43d2-9db3-ca361aa36b1c.png")
                .build());
        departementRepository.save(Department.builder()
                .name("법학부")
                .profile("https://user-images.githubusercontent.com/63008958/203982170-44a91016-1b6a-4d88-b7a1-4560966521f2.png")
                .build());
        departementRepository.save(Department.builder()
                .name("커뮤니케이션학부")
                .profile("https://user-images.githubusercontent.com/63008958/203982168-d3cba5e1-2627-4941-a414-aa3e17fd2959.png")
                .build());
        departementRepository.save(Department.builder()
                .name("상담심리사회복지학부")
                .profile("https://user-images.githubusercontent.com/63008958/203982129-0ed8a671-c04a-443f-b48e-7471074112ea.png")
                .build());
        departementRepository.save(Department.builder()
                .name("공간환경시스템공학부")
                .profile("https://user-images.githubusercontent.com/63008958/203982164-378d214b-04ce-4d1f-b909-36daf17b5cdb.png")
                .build());
        departementRepository.save(Department.builder()
                .name("콘텐츠융합디자인학부")
                .profile("https://user-images.githubusercontent.com/63008958/203982153-f2ae91d9-8984-40ed-9802-5b2f71f123b5.png")
                .build());
        departementRepository.save(Department.builder()
                .name("기계제어공학부")
                .profile("https://user-images.githubusercontent.com/63008958/203982161-61e66aa6-6625-4eb0-819c-71edc01f47ad.png")
                .build());
        departementRepository.save(Department.builder()
                .name("ICT 창업학부")
                .profile("https://user-images.githubusercontent.com/63008958/203982115-30963e7f-19e9-4f45-8390-bec128a1981b.png")
                .build());
        departementRepository.save(Department.builder()
                .name("생명과학부")
                .profile("https://user-images.githubusercontent.com/63008958/203982143-6972a9ad-8b43-4024-b63f-59ce0969e922.png")
                .build());
        departementRepository.save(Department.builder()
                .name("글로벌리더쉽학부")
                .profile("https://user-images.githubusercontent.com/63008958/203982177-4324201c-63d8-4df0-abe7-09fd1c7cfd21.png")
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
                .semester("2021-2")
                .personal(false)
                .remark("우수상 수상")
                .requestStatus(1)
                .name("(캠프)미리미리C 캠프_김광")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2021-2")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("공학프로젝트기획")
                .weight(2)
                .category(categoryRepository.findByName("전공마일리지"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2021-2")
                .personal(true)
                .remark("우수상 수상")
                .requestStatus(1)
                .name("해커톤")
                .weight(5)
                .category(categoryRepository.findByName("기타"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2021-2")
                .personal(true)
                .remark("")
                .requestStatus(1)
                .name("정보처리기사 자격증")
                .weight(4)
                .category(categoryRepository.findByName("기타"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2021-2")
                .personal(false)
                .remark("우수상 수상")
                .requestStatus(1)
                .name("(캠프)Advanced Flutter Camp_조성배")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2021-2")
                .personal(true)
                .remark("")
                .requestStatus(1)
                .name("현장실습")
                .weight(5)
                .category(categoryRepository.findByName("산학마일리지"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2021-2")
                .personal(true)
                .remark("")
                .requestStatus(1)
                .name("논문 분석")
                .weight(3)
                .category(categoryRepository.findByName("비교과-연구활동"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2021-2")
                .personal(false)
                .remark("디지털 시대와 개발자 (10.26)")
                .requestStatus(1)
                .name("CSEE 특강")
                .weight(3)
                .category(categoryRepository.findByName("비교과-특강참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2021-2")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("캡스톤 페스티벌 참여")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2021-2")
                .personal(false)
                .remark("미리미리 C 캠프")
                .requestStatus(1)
                .name("CRA")
                .weight(3)
                .category(categoryRepository.findByName("비교과-학회활동"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2021-2")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("GHOST GBC")
                .weight(4)
                .category(categoryRepository.findByName("비교과-학회활동"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2021-2")
                .personal(true)
                .remark("")
                .requestStatus(1)
                .name("네이버 부트캠프")
                .weight(5)
                .category(categoryRepository.findByName("기타"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2021-2")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("(캠프)프로그래밍 집중훈련 캠프_김호준")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2021-2")
                .personal(false)
                .remark("프로그래머로 산다는 것 (11.2)")
                .requestStatus(1)
                .name("CSEE 특강")
                .weight(3)
                .category(categoryRepository.findByName("비교과-특강참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2021-2")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("(캠프)파이썬 기초 잡기 캠프_박성진")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2021-2")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("(캠프)Program Problem Solving 캠프_김광")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2021-2")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("(캠프)VVS_세상 소중한 나를 위한 캠프_정석민")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2021-2")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("(캠프)에메르송의 코딩테스트 준비 캠프_송다빈")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2021-2")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("(캠프)딥러닝 심화 캠프_김인중")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-1")
                .personal(false)
                .remark("Spring 이용")
                .requestStatus(1)
                .name("(캠프)웹서비스 프로젝트(spring)_장소연")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-1")
                .personal(false)
                .remark("우수상 수상")
                .requestStatus(1)
                .name("(캠프)미리미리C 캠프_김광")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-1")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("공학설계입문")
                .weight(1)
                .category(categoryRepository.findByName("전공마일리지"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-1")
                .personal(true)
                .remark("최우수상 수상")
                .requestStatus(1)
                .name("해커톤")
                .weight(5)
                .category(categoryRepository.findByName("기타"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-1")
                .personal(true)
                .remark("")
                .requestStatus(1)
                .name("ISQTB 자격증")
                .weight(2)
                .category(categoryRepository.findByName("기타"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-1")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("(캠프)Advanced Flutter Camp_조성배")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-1")
                .personal(true)
                .remark("BizFlow")
                .requestStatus(1)
                .name("현장실습")
                .weight(5)
                .category(categoryRepository.findByName("산학마일리지"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-1")
                .personal(true)
                .remark("AI 딥러닝")
                .requestStatus(1)
                .name("논문 분석")
                .weight(3)
                .category(categoryRepository.findByName("비교과-연구활동"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-1")
                .personal(false)
                .remark("제네시스랩의 기술 창업 스토리")
                .requestStatus(1)
                .name("CSEE 특강")
                .weight(2)
                .category(categoryRepository.findByName("비교과-특강참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-1")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("공학 페스티벌 참여")
                .weight(1)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-1")
                .personal(false)
                .remark("미리미리 C 캠프")
                .requestStatus(1)
                .name("CRA")
                .weight(1)
                .category(categoryRepository.findByName("비교과-학회활동"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-1")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("슬기짜기 코딩 교육")
                .weight(1)
                .category(categoryRepository.findByName("비교과-학회활동"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-1")
                .personal(true)
                .remark("세상을 바꾸는 디지털 전환과 신기술 도입 사례 (04.16)")
                .requestStatus(1)
                .name("CSEE 특강")
                .weight(1)
                .category(categoryRepository.findByName("비교과-특강참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-1")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("(캠프)프로그래밍 캠프_김호준")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-1")
                .personal(false)
                .remark("글로벌 라이다 기술 동향 및 에스오에스랩 창업 스토리 (04.23)")
                .requestStatus(1)
                .name("CSEE 특강")
                .weight(3)
                .category(categoryRepository.findByName("비교과-특강참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-1")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("(캠프)파이썬 심화 캠프_박성진")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-1")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("(캠프)Program Problem Solving 캠프_김광")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-1")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("(캠프)VVS_프로그램 기획 및 설계 캠프_정석민")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-1")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("(캠프)에메르송의 면접 준비 캠프_송다빈")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-1")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("(캠프)컴퓨터 비전 캠프_황성수")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-2")
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
                .name("(캠프)미리미리C 캠프_김광")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-2")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("랩 설명회 참여")
                .weight(1)
                .category(categoryRepository.findByName("전공마일리지"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-2")
                .personal(true)
                .remark("장려상 수상")
                .requestStatus(1)
                .name("해커톤")
                .weight(5)
                .category(categoryRepository.findByName("기타"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-2")
                .personal(true)
                .remark("")
                .requestStatus(1)
                .name("SQLD 자격증")
                .weight(3)
                .category(categoryRepository.findByName("기타"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-2")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("(캠프)Advanced Flutter Camp_조성배")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-2")
                .personal(true)
                .remark("카카오")
                .requestStatus(1)
                .name("현장실습")
                .weight(5)
                .category(categoryRepository.findByName("산학마일리지"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-2")
                .personal(true)
                .remark("Brain Computer Interface")
                .requestStatus(1)
                .name("논문 분석")
                .weight(3)
                .category(categoryRepository.findByName("비교과-연구활동"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-2")
                .personal(false)
                .remark("웹과 클라우드 개발자 취업의 현장 (11.07)")
                .requestStatus(1)
                .name("CSEE 특강")
                .weight(3)
                .category(categoryRepository.findByName("비교과-특강참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-2")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("커리어 페스티벌 참여")
                .weight(1)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-2")
                .personal(false)
                .remark("미리미리 C 캠프")
                .requestStatus(1)
                .name("CRA")
                .weight(1)
                .category(categoryRepository.findByName("비교과-학회활동"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-2")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("SODA 코딩 교육")
                .weight(1)
                .category(categoryRepository.findByName("비교과-학회활동"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-2")
                .personal(true)
                .remark("웹 개발자로 살아남는 법 (11.27)")
                .requestStatus(1)
                .name("CSEE 특강")
                .weight(1)
                .category(categoryRepository.findByName("비교과-특강참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-2")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("(캠프)프로그래밍 캠프_김호준")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-2")
                .personal(false)
                .remark("빅데이터 분석과 활용법 (12.10)")
                .requestStatus(1)
                .name("CSEE 특강")
                .weight(1)
                .category(categoryRepository.findByName("비교과-특강참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-2")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("(캠프)파이썬 심화 캠프_박성진")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-2")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("(캠프)Program Problem Solving 캠프_김광")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-2")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("(캠프)VVS_프로그램 기획 및 설계 캠프_정석민")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-2")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("(캠프)에메르송의 면접 준비 캠프_송다빈")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
        activityRepository.save(Activity.builder()
                .semester("2022-2")
                .personal(false)
                .remark("")
                .requestStatus(1)
                .name("(캠프)컴퓨터 비전 캠프_황성수")
                .weight(3)
                .category(categoryRepository.findByName("비교과-행사참여"))
                .build());
    }


    private void saveMajor() {
        majorRepository.save(Major.builder().name("전자공학전공").profile("https://user-images.githubusercontent.com/63008958/204516545-6c7ca818-1c99-4c1a-a4d4-569577d344d8.jpg").build());
        majorRepository.save(Major.builder().name("전자공학심화전공").profile("https://user-images.githubusercontent.com/63008958/204516569-13f7310b-b5a5-4f9d-8171-065884cbf59c.jpg").build());
        majorRepository.save(Major.builder().name("Information Technology").profile("https://user-images.githubusercontent.com/63008958/204516579-bad0d03e-d9ca-4a76-9e94-4a31fe605ce4.jpg").build());
        majorRepository.save(Major.builder().name("ICT 융합전공").profile("https://user-images.githubusercontent.com/63008958/204516638-7c5c13c2-ebb7-4d41-895b-8af61801e7b4.jpg").build());
        majorRepository.save(Major.builder().name("AI Convergence & Entrepreneurship 전공").profile("https://user-images.githubusercontent.com/63008958/204517597-e463b42b-17f3-4476-9314-fe33edb23e8e.jpg").build());
        majorRepository.save(Major.builder().name("Global Entrepreneurship 전공").profile("https://user-images.githubusercontent.com/63008958/204516633-5be00a69-d31e-4edb-ada1-9f143f9ec5ee.jpg").build());
        majorRepository.save(Major.builder().name("AI 융합 전공").profile("https://user-images.githubusercontent.com/63008958/204517614-2fd82907-7e30-488e-8df5-b34c1f5fd05f.jpg").build());
        majorRepository.save(Major.builder().name("데이터 사이언스 전공").profile("https://user-images.githubusercontent.com/63008958/204516681-cee59ca5-3e83-45b0-9f2d-77d38aa9772a.jpg").build());
        majorRepository.save(Major.builder().name("경영학전공").profile("https://user-images.githubusercontent.com/63008958/204516699-8d4ae330-79ce-4827-a9b1-4c6bb94a04dd.jpg").build());
        majorRepository.save(Major.builder().name("경제학전공").profile("https://user-images.githubusercontent.com/63008958/204516724-255d3f3d-586c-44f2-bf30-20ae74500e5e.jpg").build());
        majorRepository.save(Major.builder().name("Global Management").profile("https://user-images.githubusercontent.com/63008958/204516745-795ffbf4-3e25-410a-bb87-8957b17f974a.jpg").build());
        majorRepository.save(Major.builder().name("국제지역학전공").profile("https://user-images.githubusercontent.com/63008958/204516767-6d058e2b-98eb-4156-84f2-41adc5961487.jpg").build());
        majorRepository.save(Major.builder().name("영어전공").profile("https://user-images.githubusercontent.com/63008958/204516780-97509626-d605-4b33-871e-6c66d8b9bccb.jpg").build());
        majorRepository.save(Major.builder().name("한국법전공").profile("").build());
        majorRepository.save(Major.builder().name("US & International Law").profile("").build());
        majorRepository.save(Major.builder().name("상담심리학전공").profile("").build());
        majorRepository.save(Major.builder().name("사회복지학전공").profile("").build());
        majorRepository.save(Major.builder().name("공연영상학전공").profile("").build());
        majorRepository.save(Major.builder().name("언론정보학전공").profile("").build());
        majorRepository.save(Major.builder().name("글로벌한국학전공").profile("").build());
    }
}
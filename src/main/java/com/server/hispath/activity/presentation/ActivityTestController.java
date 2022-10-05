package com.server.hispath.activity.presentation;


import com.server.hispath.activity.domain.Activity;
import com.server.hispath.activity.domain.repository.ActivityRepository;
import com.server.hispath.category.domain.Category;
import com.server.hispath.category.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activity")
public class ActivityTestController {

    private final ActivityRepository activityRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/init")
    public ResponseEntity<Void> testInit() {
        activityRepository.save(Activity.builder().semester("2021-2").personal(false).remark("Spring 이용").requestStatus(200).name("(캠프)웹서비스 프로젝트(spring)_장소연").weight(3).category(categoryRepository.findByName("비교과-행사참여")).build());
        activityRepository.save(Activity.builder().semester("2022-2").personal(false).remark("우수상 수상").requestStatus(200).name("비즈플로우").weight(2).category(categoryRepository.findByName("비교과-행사참여")).build());
        activityRepository.save(Activity.builder().semester("2020-2").personal(false).remark("").requestStatus(200).name("(캠프)미리미리C 캠프_김광").weight(5).category(categoryRepository.findByName("비교과-행사참여")).build());
        activityRepository.save(Activity.builder().semester("2020-1").personal(false).remark("").requestStatus(200).name("C 프로그래밍").weight(1).category(categoryRepository.findByName("전공마일리지")).build());
        activityRepository.save(Activity.builder().semester("2021-1").personal(false).remark("").requestStatus(200).name("AI프로젝트 입문").weight(2).category(categoryRepository.findByName("전공마일리지")).build());
        activityRepository.save(Activity.builder().semester("2019-2").personal(false).remark("").requestStatus(200).name("(캠프)Advanced Flutter Camp_조성배").weight(4).category(categoryRepository.findByName("비교과-행사참여")).build());
        activityRepository.save(Activity.builder().semester("2019-1").personal(true).remark("").requestStatus(200).name("정보처리기사 자격증").weight(2).category(categoryRepository.findByName("기타")).build());
        activityRepository.save(Activity.builder().semester("2022-1").personal(false).remark("").requestStatus(200).name("공학프로젝트기획").weight(6).category(categoryRepository.findByName("전공마일리지")).build());
        activityRepository.save(Activity.builder().semester("2020-2").personal(false).remark("").requestStatus(200).name("캡스톤").weight(1).category(categoryRepository.findByName("전공마일리지")).build());
        activityRepository.save(Activity.builder().semester("2021-2").personal(false).remark("").requestStatus(200).name("프로그래밍 집중훈련 캠프_김호준").weight(7).category(categoryRepository.findByName("비교과-행사참여")).build());



        return ResponseEntity.ok(null);
    }
}

package com.server.hispath.activity.application;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.server.hispath.activity.application.dto.ActivityContentDto;
import com.server.hispath.activity.application.dto.ActivityDto;
import com.server.hispath.activity.application.dto.SemesterDto;
import com.server.hispath.activity.domain.Activity;
import com.server.hispath.activity.domain.repository.ActivityRepository;
import com.server.hispath.category.application.CategoryService;
import com.server.hispath.category.domain.Category;
import com.server.hispath.exception.activity.ActivityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final CategoryService categoryService;

    @Transactional
    public Long create(Long categoryId, ActivityContentDto dto) {
        Category category = categoryService.findById(categoryId);
        Activity activity = Activity.from(category, dto);
        Activity savedActivity = activityRepository.save(activity);
        return savedActivity.getId();
    }
    @Transactional(readOnly = true)
    public ActivityDto find(Long id) {
        Activity activity = this.findById(id);
        return ActivityDto.from(activity);
    }



    @Transactional(readOnly = true)
    public List<ActivityDto> findAll() {
        List<Activity> activities = activityRepository.findAll();
        return activities.stream()
                         .map(ActivityDto::from)
                         .collect(Collectors.toList());
    }

    @Transactional
    public ActivityDto update(Long id, Long categoryId, ActivityContentDto dto){
        Activity activity = this.findById(id);
        Category category = categoryService.findById(categoryId);
        activity.update(category, dto);

        return ActivityDto.from(activity);
    }

    @Transactional
    public void delete(Long id){
        activityRepository.deleteById(id);
    }

    public Activity findById(Long id) {
        return activityRepository.findById(id).orElseThrow(ActivityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<SemesterDto> bringSemester() {
        Optional<Activity> semesters = activityRepository.bringSemester();
        return semesters.stream()
                .map(SemesterDto::from)
                .collect(Collectors.toList());

    }
}

package com.server.hispath.activity.application;

import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.activity.application.dto.ActivityDto;
import com.server.hispath.activity.application.dto.MActivityContentDto;
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
public class MActivityService {
    private final ActivityRepository activityRepository;
    private final CategoryService categoryService;

    @Transactional
    public Long create(MActivityContentDto dto) {
        Category category = categoryService.findById(dto.getCategoryId());
        Activity activity = Activity.from(category, dto);
        Activity savedActivity = activityRepository.save(activity);
        return savedActivity.getId();
    }

    @Transactional
    public void createAll(List<MActivityContentDto> dtos) {
        List<Activity> activities = dtos.stream()
                                        .map(dto -> {
                                            Category category = categoryService.findById(dto.getCategoryId());
                                            return Activity.from(category, dto);
                                        }).collect(Collectors.toList());
        activityRepository.saveAll(activities);
    }

    @Transactional
    public ActivityDto update(Long id, MActivityContentDto dto){
        Activity activity = activityRepository.findById(id).orElseThrow(ActivityNotFoundException::new);
        Category category = categoryService.findById(dto.getCategoryId());
        activity.update(category, dto);
        return ActivityDto.from(activity);
    }
}

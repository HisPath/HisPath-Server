package com.server.hispath.activity.application;

import com.server.hispath.activity.application.dto.MActivityContentDto;
import com.server.hispath.activity.domain.Activity;
import com.server.hispath.activity.domain.repository.ActivityRepository;
import com.server.hispath.category.application.CategoryService;
import com.server.hispath.category.domain.Category;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MActivityService {
    private final ActivityRepository activityRepository;
    private final CategoryService categoryService;

    @Transactional
    public Long create(Long categoryId, MActivityContentDto dto) {
        Category category = categoryService.findById(categoryId);
        Activity activity = Activity.from(category, dto);
        Activity savedActivity = activityRepository.save(activity);
        return savedActivity.getId();
    }
}

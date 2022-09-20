package com.server.hispath.category.application;

import com.server.hispath.category.application.dto.CategoryContentDto;
import com.server.hispath.category.domain.Category;
import com.server.hispath.category.domain.repository.CategoryRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long create(CategoryContentDto dto){
        Category savedCategory = categoryRepository.save(Category.from(dto));
        return savedCategory.getId();
    }
}

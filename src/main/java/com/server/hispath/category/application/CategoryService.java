package com.server.hispath.category.application;

import com.server.hispath.category.application.dto.CategoryContentDto;
import com.server.hispath.category.application.dto.CategoryCreateDto;
import com.server.hispath.category.domain.Category;
import com.server.hispath.category.domain.repository.CategoryRepository;
import com.server.hispath.exception.category.CategoryNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long create(CategoryCreateDto dto){
        Category savedCategory = categoryRepository.save(Category.from(dto));
        return savedCategory.getId();
    }

    @Transactional(readOnly = true)
    public CategoryContentDto findById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        return CategoryContentDto.from(category);
    }
}

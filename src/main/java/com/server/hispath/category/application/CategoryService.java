package com.server.hispath.category.application;

import com.server.hispath.category.domain.repository.CategoryRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

}

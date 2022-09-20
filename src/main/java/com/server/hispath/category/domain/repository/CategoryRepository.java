package com.server.hispath.category.domain.repository;

import com.server.hispath.category.domain.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

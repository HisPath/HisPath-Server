package com.server.hispath.major.domain.repository;

import com.server.hispath.major.domain.Major;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MajorRepository extends JpaRepository<Major, Long> {
    Major findByName(String name);
}

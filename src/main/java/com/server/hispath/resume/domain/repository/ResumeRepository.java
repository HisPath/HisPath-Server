package com.server.hispath.resume.domain.repository;

import com.server.hispath.resume.domain.Resume;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}

package com.server.hispath.scholarship.domain.repository;

import com.server.hispath.scholarship.domain.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {

}


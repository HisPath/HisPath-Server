package com.server.hispath.department.domain.repository;

import java.util.Optional;

import com.server.hispath.department.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);
}

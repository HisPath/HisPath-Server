package com.server.hispath.manager.domain.repository;

import java.util.Optional;

import com.server.hispath.manager.domain.Manager;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findByEmail(String email);
}

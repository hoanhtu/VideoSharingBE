package com.tuho.YouTubeSharingApp.repository;

import com.tuho.YouTubeSharingApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

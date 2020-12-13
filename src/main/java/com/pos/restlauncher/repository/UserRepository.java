package com.pos.restlauncher.repository;

import java.util.Optional;

import com.pos.restlauncher.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);

}

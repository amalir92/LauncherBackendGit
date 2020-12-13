package com.pos.restlauncher.repository;

import com.pos.restlauncher.model.Terminal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TerminalRepository extends JpaRepository<Terminal, String> {
    
}

package com.pos.restlauncher.controller;

import java.util.List;

import com.pos.restlauncher.model.Terminal;
import com.pos.restlauncher.repository.TerminalRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/rest/terminals")
public class TerminalController {

    private final TerminalRepository repository;

    TerminalController(TerminalRepository repository) {
    this.repository = repository;
  }

  // Aggregate root

  @GetMapping("/payments")
  List<Terminal> all() {
    return repository.findAll();
  }

  @PostMapping("/terminals")
  @ResponseStatus(HttpStatus.CREATED)
  Terminal newTerminal(@RequestBody Terminal newTerminal) {
    return repository.save(newTerminal);
  }                                                                                                                                                                                                                                             
    
}

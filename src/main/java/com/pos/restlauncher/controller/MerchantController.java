package com.pos.restlauncher.controller;

import java.util.List;

import com.pos.restlauncher.Exception.MerchantNotFoundException;
import com.pos.restlauncher.model.Merchant;
import com.pos.restlauncher.repository.MerchantRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/rest/merchants")
public class MerchantController {
    private final MerchantRepository repository;

    MerchantController(MerchantRepository repository) {
    this.repository = repository;
  }

  // Aggregate root

  @GetMapping("/merchants")
  List<Merchant> all() {
    return repository.findAll();
  }

  @PostMapping("/merchants")
  @ResponseStatus(HttpStatus.CREATED)
  Merchant newMerchant(@RequestBody Merchant newMerchant) {
    return repository.save(newMerchant);
  }

  // Single item

  @GetMapping("/merchants/{id}")
  Merchant one(@PathVariable String id) {

    return repository.findById(id)
      .orElseThrow(() -> new MerchantNotFoundException(id));
  }

  @PutMapping("/merchants/{id}")
  Merchant updatemerchant(@RequestBody Merchant newMerchant, @PathVariable String id) {

    return repository.findById(id)
      .map(merchant -> {
        merchant.setTerminalId(newMerchant.getTerminalId());
        merchant.setDeviceId(newMerchant.getDeviceId());
        merchant.setStartYear(newMerchant.getStartYear());
        merchant.setStartMonth(newMerchant.getStartMonth());
        merchant.setStartDate(newMerchant.getStartDate());
        merchant.setDuration(newMerchant.getDuration());

        return repository.save(merchant);
      })
      .orElseGet(() -> {
        newMerchant.setMerchantId(id);
        return repository.save(newMerchant);
      });
  }

  @PutMapping("/merchants/{id}/{isActive}")
  void setActiveStatus(@PathVariable String id,@PathVariable boolean isActive) {

     repository.findById(id)
      .map(merchant -> {
        merchant.setActive(isActive);
        return repository.save(merchant);
      });
  }

  @PutMapping("/merchants/{id}/{terminalId}")
  void setTerminalId(@PathVariable String id,@PathVariable String terminalId) {

     repository.findById(id)
      .map(merchant -> {
        merchant.setTerminalId(terminalId);
        return repository.save(merchant);
      });
  }

  @PutMapping("/merchants/{id}/{deviceId}")
  void setDeviceId(@PathVariable String id,@PathVariable String deviceId) {

     repository.findById(id)
      .map(merchant -> {
        merchant.setTerminalId(deviceId);
        return repository.save(merchant);
      });
  }

  @DeleteMapping("/merchants/{id}")
  void deleteMerchant(@PathVariable String id) {
    repository.deleteById(id);
  }
}

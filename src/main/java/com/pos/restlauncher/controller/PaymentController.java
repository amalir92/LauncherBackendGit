package com.pos.restlauncher.controller;

import java.util.List;

import com.pos.restlauncher.Exception.PaymentNotFoundException;
import com.pos.restlauncher.model.Payment;
import com.pos.restlauncher.repository.PaymentRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/rest/payments")
public class PaymentController {
    private final PaymentRepository repository;

    PaymentController(PaymentRepository repository) {
    this.repository = repository;
  }

  // Aggregate root

  @GetMapping("/payments")
  List<Payment> all() {
    return repository.findAll();
  }

  @GetMapping("/payments/{merchantId}")
  List<Payment> getByMerchantId(@PathVariable String merchantId) {
    return repository.findByMerchantId(merchantId);
  }

  @GetMapping("/payments/{terminalId}")
  List<Payment> getByTerminalId(@PathVariable String terminalId) {
    return repository.findByTerminalId(terminalId);
  }

  @PostMapping("/payments")
  @ResponseStatus(HttpStatus.CREATED)
  Payment newPayment(@RequestBody Payment newPayment) {
    return repository.save(newPayment);
  }
    
  @GetMapping("/payments/{id}")
  Payment one(@PathVariable Long id) {

    return repository.findById(id)
      .orElseThrow(() -> new PaymentNotFoundException(id));
  }

  @PutMapping("/payments/{id}")
  Payment updatePayment(@RequestBody Payment newPayment, @PathVariable Long id) {

    return repository.findById(id)
      .map(payment -> {
        payment.setNextPaymentDate(newPayment.getNextPaymentDate());
        payment.setPaymentAmount(newPayment.getPaymentAmount());
        payment.setPaymentType(newPayment.getPaymentType());

        return repository.save(payment);
      })
      .orElseGet(() -> {
        newPayment.setPaymentId(id);
        return repository.save(newPayment);
      });
  }

  @DeleteMapping("/payments/{id}")
  void deleteUser(@PathVariable Long id) {
    repository.deleteById(id);
  }
}

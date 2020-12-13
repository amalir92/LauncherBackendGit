package com.pos.restlauncher.repository;

import java.util.List;

import com.pos.restlauncher.model.Payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long>{

    List<Payment> findByMerchantId(String merchantId);

    List<Payment> findByTerminalId(String merchantId);
   
}

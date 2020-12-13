package com.pos.restlauncher.repository;

import com.pos.restlauncher.model.Merchant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, String> {

}

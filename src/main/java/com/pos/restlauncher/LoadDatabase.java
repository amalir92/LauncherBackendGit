package com.pos.restlauncher;

import com.pos.restlauncher.model.Payment;
import com.pos.restlauncher.model.User;
import com.pos.restlauncher.model.PaymentType;
import com.pos.restlauncher.model.Terminal;

import java.util.Arrays;

import com.pos.restlauncher.model.Merchant;
import com.pos.restlauncher.repository.MerchantRepository;
import com.pos.restlauncher.repository.PaymentRepository;
import com.pos.restlauncher.repository.TerminalRepository;
import com.pos.restlauncher.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(MerchantRepository merchantRepository,TerminalRepository terminalRepository,PaymentRepository paymentRepository,PasswordEncoder passwordEncoder,UserRepository userRepository) {

    return args -> {
    log.info("Preloading " + terminalRepository.save(new Terminal("0001XT","001","D1")));
     log.info("Preloading " + merchantRepository.save(new Merchant("0001",2013,10,12, null,true)));
     log.info("Preloading " + merchantRepository.save(new Merchant("0002",2014,10,12, null,true)));
     log.info("Preloading " + paymentRepository.save(new Payment("0001","0001XT",null,100000,PaymentType.IPG,"qwqewewrw001")));
     log.info("PreLoading "+  userRepository.save(User.builder()
     .username("user")
     .password(passwordEncoder.encode("password"))
     .roles(Arrays.asList( "ROLE_USER"))
     .build()
 ));

 log.info("PreLoading "+userRepository.save(User.builder()
 .username("admin")
 .password(passwordEncoder.encode("password"))
 .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
 .build()
));



    };
  }
  
}
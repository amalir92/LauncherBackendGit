package com.pos.restlauncher.config;

import java.util.ArrayList;

import com.pos.restlauncher.security.JwtSecurityConfigurer;
import com.pos.restlauncher.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SpringSecurtyConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    // Create 2 users for demo
  /*  @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");

    }*/

    // Secure the endpoins with HTTP Basic authentication
  /*  @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off

        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/auth/signin").permitAll()
                .antMatchers(HttpMethod.GET, "/rest/merchants/merchants**").hasAnyRole("USER","ADMIN")
               .antMatchers(HttpMethod.DELETE, "/rest/merchants/merchants**").hasRole("ADMIN")
               .antMatchers(HttpMethod.POST, "/rest/merchants/merchants**").hasRole("ADMIN")
               .antMatchers(HttpMethod.PUT, "/rest/merchants/merchants**").hasRole("ADMIN")
               .antMatchers(HttpMethod.POST, "/rest/payments/payments**").hasRole("ADMIN")
               .antMatchers(HttpMethod.PUT, "/rest/payments/payments**").hasRole("ADMIN")
               .antMatchers(HttpMethod.GET, "/rest/payments/payments**").hasAnyRole("USER","ADMIN")
               .antMatchers(HttpMethod.POST, "/rest/terminals/terminals**").hasRole("ADMIN")
               .antMatchers(HttpMethod.GET, "/rest/terminals/terminals**").hasAnyRole("USER","ADMIN")
          //     .antMatchers(HttpMethod.GET, "/rest/merchants/merchants**").permitAll()
                .anyRequest().authenticated()
            .and()
            .apply(new JwtSecurityConfigurer(jwtTokenProvider));
        //@formatter:on
    }

    
}

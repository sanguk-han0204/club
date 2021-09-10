package com.example.club.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {
@Bean
PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}

@Override
protected void configure(HttpSecurity http) throws Exception{
    http.authorizeRequests()
            .antMatchers("/sample/all").permitAll()
            .antMatchers("/sample/member").hasRole("USER");
    http.formLogin();
    http.csrf().disable();
    http.logout();
}/*

@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // 사용자 계정은 user1
    auth.inMemoryAuthentication().withUser("user1")
            //1111패스워드인코딩 결과
    .password("$2a$10$9rKXeulASv.zuDTfoFnfje0Mab.FGXiA/TSJ0pqgksnW2lNGzLPBK")
            .roles("USER");
}*/
}

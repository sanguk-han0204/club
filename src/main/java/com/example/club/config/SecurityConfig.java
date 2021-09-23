package com.example.club.config;

import com.example.club.security.handler.ClubLoginSuccessHandler;
import com.example.club.security.service.ClubUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ClubUserDetailsService userDetailsService;

    @Bean
PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}

@Override
protected void configure(HttpSecurity http) throws Exception{
/*
    http.authorizeRequests()
            .antMatchers("/sample/all").permitAll()
            .antMatchers("/sample/member").hasRole("USER");
*/
    http.formLogin();
    http.csrf().disable();
    http.oauth2Login().successHandler(successHandler());
    http.rememberMe().tokenValiditySeconds(60*60*7).userDetailsService(userDetailsService);
}
@Bean
    public ClubLoginSuccessHandler successHandler(){
    return new ClubLoginSuccessHandler(passwordEncoder());


}
/*

@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // 사용자 계정은 user1
    auth.inMemoryAuthentication().withUser("user1")
            //1111패스워드인코딩 결과
    .password("$2a$10$9rKXeulASv.zuDTfoFnfje0Mab.FGXiA/TSJ0pqgksnW2lNGzLPBK")
            .roles("USER");
}*/
}

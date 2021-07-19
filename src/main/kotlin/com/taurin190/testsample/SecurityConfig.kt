package com.taurin190.testsample

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter(){
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(
            "/**/favicon.ico",
            "/images/**",
            "/css/**",
            "/js/**"
        )
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/", "/rest/**", "/mvc/**", "/mvc2/**",  "/auth/login").permitAll()
            .anyRequest().authenticated()
    }
}
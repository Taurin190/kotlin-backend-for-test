package com.taurin190.testsample

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User

@Service
class AuthService: UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return User.withUsername("test")
            .password("test")
            .roles("ADMIN")
            .build()
    }
}
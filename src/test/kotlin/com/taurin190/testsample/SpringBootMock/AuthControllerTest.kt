package com.taurin190.testsample.SpringBootMock

import com.ninjasquad.springmockk.MockkBean
import com.taurin190.testsample.AuthService
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @MockkBean
    private lateinit var authService: AuthService

    @Test
    fun testShowAuthLogin() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/auth/login"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
            .andExpect(MockMvcResultMatchers.model()
                .attributeExists("loginForm"))
    }

    @Test
    fun testPostAuthLogin() {
        every {
            authService.loadUserByUsername(any())
        } returns User.withUsername("test")
            .password(passwordEncoder.encode("test"))
            .roles("ADMIN")
            .build()

        mockMvc.perform(
            SecurityMockMvcRequestBuilders.formLogin()
                .loginProcessingUrl("/auth/login")
                .user("test")
                .password("test"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection)
            .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
            .andExpect(SecurityMockMvcResultMatchers.authenticated().withUsername("test"))
    }
}
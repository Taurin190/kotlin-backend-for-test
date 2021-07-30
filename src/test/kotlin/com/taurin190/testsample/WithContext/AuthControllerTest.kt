package com.taurin190.testsample.WithContext

import com.ninjasquad.springmockk.MockkBean
import com.taurin190.testsample.AuthController
import com.taurin190.testsample.AuthService
import com.taurin190.testsample.LoginForm
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl

@ExtendWith(SpringExtension::class)
@WebMvcTest(AuthController::class)
class AuthControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

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
        val loginForm = LoginForm()
        loginForm.username = "test"
        loginForm.password = "test"
        every {
            authService.loadUserByUsername(any())
        } returns User.withUsername("test")
            .password(BCryptPasswordEncoder().encode("test"))
            .roles("ADMIN")
            .build()

        mockMvc.perform(
            formLogin()
                .loginProcessingUrl("/auth/login")
                .user("test")
                .password("test"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection)
            .andExpect(redirectedUrl("/"))
            .andExpect(authenticated().withUsername("test"))
    }
}
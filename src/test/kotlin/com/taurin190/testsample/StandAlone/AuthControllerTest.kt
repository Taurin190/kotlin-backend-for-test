package com.taurin190.testsample.StandAlone

import com.taurin190.testsample.AuthController
import com.taurin190.testsample.StandaloneMvcTestViewResolver
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class AuthControllerTest {
    private lateinit var mockMvc: MockMvc

    private val authController = AuthController()

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders
            .standaloneSetup(authController)
            .setViewResolvers(StandaloneMvcTestViewResolver())
            .build()
    }

    @Test
    fun testShowAuthLogin() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/auth/login"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
            .andExpect(MockMvcResultMatchers.model()
                .attributeExists("loginForm"))
    }
}
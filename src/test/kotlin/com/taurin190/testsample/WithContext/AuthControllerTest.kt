package com.taurin190.testsample.WithContext

import com.taurin190.testsample.AuthController
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest(AuthController::class)
class AuthControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun testShowMvcIndexWithName() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/auth/login"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
            .andExpect(MockMvcResultMatchers.model()
                .attributeExists("loginForm"))
    }
}
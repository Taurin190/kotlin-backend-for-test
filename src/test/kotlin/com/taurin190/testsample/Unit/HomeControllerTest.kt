package com.taurin190.testsample.Unit

import com.taurin190.testsample.HomeController
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

class HomeControllerTest {
    private lateinit var mockMvc: MockMvc

    private val homeController = HomeController()

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build()
    }

    @Test
    fun testShowIndex() {
        mockMvc.perform(
            get("/"))
            .andDo(print())
            .andExpect(status().is2xxSuccessful)
    }
}
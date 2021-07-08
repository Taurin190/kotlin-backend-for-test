package com.taurin190.testsample.Unit

import com.taurin190.testsample.MvcController
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class MvcControllerTest {
    private lateinit var mockMvc: MockMvc

    private val mvcController = MvcController()

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(mvcController).build()
    }

    @Test
    fun testShowMvcIndex() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/mvc"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    @Test
    fun testShowMvcIndexWithName() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/mvc/test"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
            .andExpect(MockMvcResultMatchers.model().attribute("name", "test"))
    }
}
package com.taurin190.testsample.SpringBootMock

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class MvcControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

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
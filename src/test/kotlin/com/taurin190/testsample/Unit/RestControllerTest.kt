package com.taurin190.testsample.Unit

import com.taurin190.testsample.RestController
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

class RestControllerTest {
    private lateinit var mockMvc: MockMvc

    private val restController = RestController()

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(restController).build()
    }

    @Test
    fun testShowIndex() {
        mockMvc.perform(
            get("/"))
            .andDo(print())
            .andExpect(status().is2xxSuccessful)
            .andExpect(content().string(containsString("index")))
    }
}
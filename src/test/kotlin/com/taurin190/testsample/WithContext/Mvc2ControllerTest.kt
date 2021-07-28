package com.taurin190.testsample.WithContext

import com.ninjasquad.springmockk.MockkBean
import com.taurin190.testsample.AuthService
import com.taurin190.testsample.Mvc2Controller
import com.taurin190.testsample.SampleService
import io.mockk.every
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
@WebMvcTest(Mvc2Controller::class)
class Mvc2ControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var sampleService: SampleService

    @MockkBean
    private lateinit var authService: AuthService

    @Test
    fun testShowMvcNameList() {
        every {
            sampleService.getNameList()
        } returns listOf("sho", "jyun", "kazunari", "masaki", "satoshi")
        mockMvc.perform(
            MockMvcRequestBuilders.get("/mvc2/name"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
            .andExpect(
                MockMvcResultMatchers.model()
                .attribute("nameList", listOf("sho", "jyun", "kazunari", "masaki", "satoshi")))
    }
}
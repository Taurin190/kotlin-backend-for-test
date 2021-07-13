package com.taurin190.testsample.SpringBootMock

import com.ninjasquad.springmockk.MockkBean
import com.taurin190.testsample.SampleService
import io.mockk.every
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
class Mvc2ControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var sampleService: SampleService

    @Test
    fun testShowMvcNameList() {
        every {
            sampleService.getNameList()
        } returns listOf("sho", "jyun", "kazunari", "masaki", "satoshi")
        mockMvc.perform(
            MockMvcRequestBuilders.get("/mvc2/name"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
            .andExpect(MockMvcResultMatchers.model()
                .attribute("nameList", listOf("sho", "jyun", "kazunari", "masaki", "satoshi")))
    }
}
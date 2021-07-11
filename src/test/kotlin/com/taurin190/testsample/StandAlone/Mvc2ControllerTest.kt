package com.taurin190.testsample.StandAlone

import com.taurin190.testsample.Mvc2Controller
import com.taurin190.testsample.SampleService
import com.taurin190.testsample.StandaloneMvcTestViewResolver
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class Mvc2ControllerTest {
    private lateinit var mockMvc: MockMvc

    @MockK
    private lateinit var sampleService: SampleService

    @InjectMockKs
    private lateinit var mvc2Controller: Mvc2Controller

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        mockMvc = MockMvcBuilders
            .standaloneSetup(mvc2Controller)
            .setViewResolvers(StandaloneMvcTestViewResolver())
            .build()
    }

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
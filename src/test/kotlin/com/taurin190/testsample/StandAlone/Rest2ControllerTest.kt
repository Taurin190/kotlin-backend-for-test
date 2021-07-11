package com.taurin190.testsample.StandAlone

import com.taurin190.testsample.Rest2Controller
import com.taurin190.testsample.SampleService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class Rest2ControllerTest {
    private lateinit var mockMvc: MockMvc

    @MockK
    private lateinit var sampleService: SampleService

    @InjectMockKs
    private lateinit var rest2Controller: Rest2Controller

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        mockMvc = MockMvcBuilders.standaloneSetup(rest2Controller).build()
    }

    @Test
    fun testName() {
        every {
            sampleService.getNameList()
        } returns listOf("sho", "jyun", "kazunari", "masaki", "satoshi")
        mockMvc.perform(
            MockMvcRequestBuilders.get("/rest/name")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
            .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("sho")))
    }
}
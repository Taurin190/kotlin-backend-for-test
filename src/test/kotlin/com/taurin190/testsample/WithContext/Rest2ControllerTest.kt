package com.taurin190.testsample.WithContext

import com.ninjasquad.springmockk.MockkBean
import com.taurin190.testsample.Rest2Controller
import com.taurin190.testsample.SampleService
import io.mockk.every
import org.hamcrest.CoreMatchers
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
@WebMvcTest(Rest2Controller::class)
class Rest2ControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var sampleService: SampleService

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
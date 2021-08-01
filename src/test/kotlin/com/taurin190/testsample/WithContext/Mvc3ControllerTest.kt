package com.taurin190.testsample.WithContext

import com.ninjasquad.springmockk.MockkBean
import com.taurin190.testsample.AuthService
import com.taurin190.testsample.Mvc2Controller
import com.taurin190.testsample.Mvc3Controller
import com.taurin190.testsample.SampleService
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest(Mvc3Controller::class)
class Mvc3ControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var sampleService: SampleService

    @MockkBean
    private lateinit var authService: AuthService

    @Test
    fun testShowMvcNameListWithoutLogin() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/mvc3/name"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection)
            .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/auth/login"))
    }

    @Test
    fun testShowMvcNameList() {
        every {
            sampleService.getNameList()
        } returns listOf("sho", "jyun", "kazunari", "masaki", "satoshi")
        mockMvc.perform(
            MockMvcRequestBuilders.get("/mvc3/name")
                .with(SecurityMockMvcRequestPostProcessors.user("test")))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
            .andExpect(MockMvcResultMatchers.model()
                .attribute("nameList", listOf("sho", "jyun", "kazunari", "masaki", "satoshi")))
    }
}
package com.taurin190.testsample.WithContext

import com.taurin190.testsample.RestController
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
@WebMvcTest(RestController::class)
class RestControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun testShowIndex() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
            .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("index")))
    }
}
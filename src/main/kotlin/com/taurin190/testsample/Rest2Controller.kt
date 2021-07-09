package com.taurin190.testsample

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class Rest2Controller(
    @Autowired private val sampleService: SampleService
) {
    @GetMapping("/name")
    fun name(): String {
        val userList = sampleService.getNameList()
        return userList.toString()
    }
}
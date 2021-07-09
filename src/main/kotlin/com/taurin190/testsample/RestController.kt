package com.taurin190.testsample

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class RestController {
    @GetMapping("/")
    fun index(): String {
        return "index"
    }
}
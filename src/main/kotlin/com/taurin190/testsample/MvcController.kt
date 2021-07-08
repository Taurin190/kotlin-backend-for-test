package com.taurin190.testsample

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/mvc")
class MvcController {
    @GetMapping("")
    fun index(): String {
        return "index"
    }

    @GetMapping("/{name}")
    fun indexWithValue(
        @PathVariable("name") name: String,
        mav: ModelAndView
    ): ModelAndView {
        mav.addObject("name", name)
        mav.viewName = "index"
        return mav
    }
}
package com.taurin190.testsample

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/mvc2")
class Mvc2Controller(
    @Autowired private val sampleService: SampleService
) {

    @GetMapping("/name")
    fun index(mav: ModelAndView): ModelAndView {
        mav.viewName= "name"
        val nameList = sampleService.getNameList()
        mav.addObject("nameList", nameList)
        return mav
    }
}
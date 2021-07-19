package com.taurin190.testsample

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/auth")
class AuthController {
    @GetMapping("/login")
    fun loginForm(mav: ModelAndView): ModelAndView {
        mav.viewName = "login"
        mav.addObject("loginForm", LoginForm())
        return mav
    }
}
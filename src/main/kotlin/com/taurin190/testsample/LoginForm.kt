package com.taurin190.testsample

import javax.validation.constraints.NotBlank

class LoginForm {
    @NotBlank
    var username: String = ""
    @NotBlank
    var password: String = ""
}
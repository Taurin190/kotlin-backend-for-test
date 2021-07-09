package com.taurin190.testsample

import org.springframework.stereotype.Service

@Service
class SampleService {
    fun getNameList(): Collection<String> {
        return listOf(
            "takuya",
            "goro",
            "masahiro",
            "shingo",
            "tsuyoshi"
        )
    }
}
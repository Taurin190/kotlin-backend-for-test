package com.taurin190.testsample

import org.springframework.web.servlet.view.AbstractUrlBasedView
import org.springframework.web.servlet.view.InternalResourceView
import org.springframework.web.servlet.view.InternalResourceViewResolver


class StandaloneMvcTestViewResolver : InternalResourceViewResolver() {
    @Throws(Exception::class)
    override fun buildView(viewName: String): AbstractUrlBasedView {
        val view = super.buildView(viewName) as InternalResourceView
        // prevent checking for circular view paths
        view.setPreventDispatchLoop(false)
        return view
    }
}
package com.jarzasa.madridshops.domain.interactors.internetstatus

import com.jarzasa.madridshops.domain.interactors.CodeClosure
import com.jarzasa.madridshops.domain.interactors.ErrorClosure


class InternetStatusInteractorImpl : InternetStatusInteractor {
    override fun execute(success: CodeClosure, error: ErrorClosure) {
        success()
    }
}
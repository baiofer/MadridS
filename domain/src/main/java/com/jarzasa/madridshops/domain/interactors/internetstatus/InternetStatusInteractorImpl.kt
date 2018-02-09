package com.jarzasa.madridshops.domain.interactors.internetstatus

import com.jarzasa.madridshops.utils.CodeClosure
import com.jarzasa.madridshops.utils.ErrorClosure


class InternetStatusInteractorImpl : InternetStatusInteractor {
    override fun execute(success: CodeClosure, error: ErrorClosure) {
        success()
    }
}
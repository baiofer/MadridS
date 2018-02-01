package com.jarzasa.madridshops.domain.interactors.internetstatus

import com.jarzasa.madridshops.domain.interactors.CodeClosure
import com.jarzasa.madridshops.domain.interactors.ErrorClosure

interface InternetStatusInteractor {
    fun execute(success: CodeClosure, error: ErrorClosure)
}

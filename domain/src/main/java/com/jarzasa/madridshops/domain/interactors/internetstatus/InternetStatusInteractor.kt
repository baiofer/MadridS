package com.jarzasa.madridshops.domain.interactors.internetstatus

import com.jarzasa.madridshops.utils.CodeClosure
import com.jarzasa.madridshops.utils.ErrorClosure

internal interface InternetStatusInteractor {
    fun execute(success: CodeClosure, error: ErrorClosure)
}

package com.jarzasa.madridshops.domain.interactors.deleteallshops

import com.jarzasa.madridshops.utils.CodeClosure
import com.jarzasa.madridshops.utils.ErrorClosure

interface DeleteAllShopsInteractor {
    fun execute(success: CodeClosure, error: ErrorClosure)
}

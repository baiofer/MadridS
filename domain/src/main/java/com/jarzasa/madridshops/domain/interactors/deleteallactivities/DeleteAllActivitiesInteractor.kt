package com.jarzasa.madridshops.domain.interactors.deleteallactivities

import com.jarzasa.madridshops.utils.CodeClosure
import com.jarzasa.madridshops.utils.ErrorClosure

interface DeleteAllActivitiesInteractor {
    fun execute(success: CodeClosure, error: ErrorClosure)
}

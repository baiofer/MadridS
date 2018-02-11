package com.jarzasa.madridshops.domain.interactors.getallactivities

import com.jarzasa.madridshops.domain.model.Activities
import com.jarzasa.madridshops.utils.ErrorCompletion
import com.jarzasa.madridshops.utils.SuccessCompletion

interface GetAllActivitiesInteractor {
    fun execute(success: SuccessCompletion<Activities>,
                error: ErrorCompletion)
}
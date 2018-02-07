package com.jarzasa.madridshops.domain.interactors.getallshops

import com.jarzasa.madridshops.repository.ErrorCompletion
import com.jarzasa.madridshops.repository.SuccessCompletion
import com.jarzasa.madridshops.domain.model.Shops

interface GetAllShopsInteractor {
    fun execute(success: SuccessCompletion<Shops>,
                error: ErrorCompletion)
}
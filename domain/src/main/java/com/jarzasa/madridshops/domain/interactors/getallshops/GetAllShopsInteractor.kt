package com.jarzasa.madridshops.domain.interactors.getallshops

import android.content.Context
import com.jarzasa.madridshops.utils.ErrorCompletion
import com.jarzasa.madridshops.utils.SuccessCompletion
import com.jarzasa.madridshops.domain.model.Shops

interface GetAllShopsInteractor {
    fun execute(success: SuccessCompletion<Shops>,
                error: ErrorCompletion)
}
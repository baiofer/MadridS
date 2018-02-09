package com.jarzasa.madridshops.domain.interactors.deleteallactivities

import android.content.Context
import com.jarzasa.madridshops.repository.RepositoryImpl
import com.jarzasa.madridshops.utils.CodeClosure
import com.jarzasa.madridshops.utils.ErrorClosure
import java.lang.ref.WeakReference

class DeleteAllActivitiesInteractorImpl(context: Context) : DeleteAllActivitiesInteractor {

    val weakContext = WeakReference<Context>(context)

    override fun execute(success: CodeClosure, error: ErrorClosure) {
        val repository = RepositoryImpl(weakContext.get()!!)
        repository.deleteAllActivities(success, error)
    }
}
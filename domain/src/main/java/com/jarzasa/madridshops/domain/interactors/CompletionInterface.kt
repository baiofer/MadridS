package com.jarzasa.madridshops.domain.interactors

interface SuccessCompletion<T> {
    fun successCompletion(e: T)
}

interface ErrorCompletion {
    fun errorCompletion(errorMessage: String)
}

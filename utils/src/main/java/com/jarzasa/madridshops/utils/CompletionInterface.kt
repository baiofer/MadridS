package com.jarzasa.madridshops.utils

interface SuccessCompletion<T> {
    fun successCompletion(e: T)
}

interface ErrorCompletion {
    fun errorCompletion(errorMessage: String)
}

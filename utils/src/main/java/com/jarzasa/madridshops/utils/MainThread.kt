package com.jarzasa.madridshops.utils

import android.os.Handler
import android.os.Looper.getMainLooper

fun DispatchOnMainThread(codeToRun: Runnable) {
    val uiHandler = Handler(getMainLooper())
    uiHandler.post(codeToRun)
}
package com.app.nexttogo.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for the Next To Go app.
 */
@HiltAndroidApp
class NextToGoApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
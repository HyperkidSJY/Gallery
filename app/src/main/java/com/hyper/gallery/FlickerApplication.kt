package com.hyper.gallery

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FlickerApplication : Application() {


    override fun onCreate() {
        super.onCreate()
    }

}
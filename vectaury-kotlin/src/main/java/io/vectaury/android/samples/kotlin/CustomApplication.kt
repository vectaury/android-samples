package io.vectaury.android.samples.kotlin

import android.app.Application
import io.vectaury.android.sdk.Tracker

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Tracker.start(this, "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx")
    }

}
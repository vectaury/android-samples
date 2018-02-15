package io.vectaury.android.samples.kotlin

import android.app.Application
import io.vectaury.android.sdk.Vectaury

class CustomApplication : Application() {

    companion object {
        const val USE_VECTAURY_SDK_PERMISSIONS_FLOW = false
    }

    override fun onCreate() {
        super.onCreate()

        Vectaury.start(this, "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx")
    }

}
/*
 * Copyright 2019 Vectaury.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.vectaury.android.samples.kotlin

import android.app.Application
import io.vectaury.android.sdk.Vectaury
import io.vectaury.cmp.VectauryConsent
import io.vectaury.cmp.VectauryConsentConfig
import io.vectaury.cmp.vendor.CustomVendor
import java.util.*

class CustomApplication : Application() {

    companion object {
        const val USE_VECTAURY_SDK_PERMISSIONS_FLOW = false
        const val USE_VECTAURY_DEBUG = false

        const val VECTAURY_VENDOR_ID = 368
        const val CUSTOM_VENDOR_ID = 1
    }

    override fun onCreate() {
        super.onCreate()

        Vectaury.setDebugMode(USE_VECTAURY_DEBUG)

        val apiKey = BuildConfig.VECTAURY_API_KEY

        // SDK CMP Initialization
        val configuration = VectauryConsentConfig.Builder(this, apiKey)
                .setLogo(R.drawable.logo_vectaury)
                .addVendor(VECTAURY_VENDOR_ID)
                .addCustomVendor(CustomVendor(CUSTOM_VENDOR_ID, "My own vendor", "https://my.own.vendor.policy", Arrays.asList(1, 2, 5), Arrays.asList(1, 2)))
                .build()
        VectauryConsent.init(this, configuration)

        // SDK GEO Initialization
        val defaultOptin = false
        Vectaury.start(this, apiKey, defaultOptin)
    }

}
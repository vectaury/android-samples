/*
 * Copyright 2018 Vectaury.
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
package io.vectaury.android.samples.cmp.kotlin

import android.app.Application
import io.vectaury.cmp.VectauryConsent
import io.vectaury.cmp.VectauryConsentConfig
import io.vectaury.cmp.purpose.CustomPublisherPurpose
import io.vectaury.cmp.purpose.IABPublisherPurpose
import io.vectaury.cmp.purpose.LocationPublisherPurpose
import io.vectaury.cmp.vendor.CustomVendor
import java.util.*

class CustomApplication : Application() {

    companion object {
        const val VECTAURY_VENDOR_ID = 368
        const val CUSTOM_VENDOR_ID = 1
        const val IAB_PURPOSE_ACESS_AND_STORAGE_ID = 1
        const val CUSTOM_PURPOSE_1_ID = 1
    }

    override fun onCreate() {
        super.onCreate()

        val configuration = VectauryConsentConfig.Builder(this, BuildConfig.VECTAURY_API_KEY)
                .setLogo(R.drawable.logo_vectaury)
                .addVendor(VECTAURY_VENDOR_ID)
                .addCustomVendor(CustomVendor(CUSTOM_VENDOR_ID, "My own vendor", "https://my.own.vendor.policy", Arrays.asList(1, 2, 5), Arrays.asList(1, 2)))
                .addPublisherPurpose(IABPublisherPurpose(this, IAB_PURPOSE_ACESS_AND_STORAGE_ID))
                .addPublisherPurpose(CustomPublisherPurpose(this, CUSTOM_PURPOSE_1_ID, getString(R.string.custom_purpose_title), getString(R.string.custom_purpose_description), -1))
                .addPublisherPurpose(LocationPublisherPurpose(this))
                .build()

        VectauryConsent.init(this, configuration)
    }

}
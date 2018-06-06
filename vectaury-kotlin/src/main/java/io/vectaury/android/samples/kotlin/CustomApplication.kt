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
package io.vectaury.android.samples.kotlin

import android.app.Application
import io.vectaury.android.sdk.Vectaury

class CustomApplication : Application() {

    companion object {
        const val USE_VECTAURY_SDK_PERMISSIONS_FLOW = false
        const val USE_VECTAURY_DEBUG = false
    }

    override fun onCreate() {
        super.onCreate()

        Vectaury.setDebugMode(USE_VECTAURY_DEBUG)

        val apiKey = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx"
        val defaultOptin = false
        Vectaury.start(this, apiKey, defaultOptin)
    }

}
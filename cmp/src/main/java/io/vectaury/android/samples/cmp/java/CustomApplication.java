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
package io.vectaury.android.samples.cmp.java;

import android.app.Application;

import io.vectaury.cmp.VectauryConsent;
import io.vectaury.cmp.VectauryConsentConfig;

public class CustomApplication extends Application {

    private static final int VECTAURY_VENDOR_ID = 368;

    @Override
    public void onCreate() {
        super.onCreate();

        VectauryConsentConfig configuration = new VectauryConsentConfig.Builder(this, BuildConfig.VECTAURY_API_KEY)
                .setLogo(R.drawable.logo_vectaury)
                .addVendor(VECTAURY_VENDOR_ID)
                .build();

        VectauryConsent.init(this, configuration);
    }
}

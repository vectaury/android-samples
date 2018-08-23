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

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import io.vectaury.cmp.VectauryConsent;
import io.vectaury.cmp.consentstring.IABConstants;
import io.vectaury.cmp.consentstring.VectauryConsentString;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("CMP - Main Screen");

        findViewById(R.id.edit_consent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VectauryConsent.display(MainActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (VectauryConsent.isFromConsent(requestCode, resultCode, data)) {
            Toast.makeText(this, "CMP closed (canIDoWhatIWant: " + canIDoWhatIWant() + ")", Toast.LENGTH_SHORT).show();
            Log.i("VectauryConsent", "Consent String: " + PreferenceManager.getDefaultSharedPreferences(this).getString(IABConstants.IAB_CONSENT_STRING, null));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean canIDoWhatIWant() {
        VectauryConsentString consentString = VectauryConsent.getVectauryConsentString(this);

        boolean iabPublisherPurposeAllowed = consentString.isPublisherPurposeAllowed(CustomApplication.IAB_PURPOSE_ACESS_AND_STORAGE_ID);
        boolean customPublisherPurposeAllowed = consentString.isPublisherCustomPurposeAllowed(CustomApplication.CUSTOM_PURPOSE_1_ID);
        boolean aCustomVendor = consentString.isCustomVendorAllowed(CustomApplication.CUSTOM_VENDOR_ID);

        return iabPublisherPurposeAllowed && customPublisherPurposeAllowed && aCustomVendor;
    }
}

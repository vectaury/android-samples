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
package io.vectaury.android.samples.java;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.List;

import io.vectaury.cmp.consentstring.ConsentStringParsingException;
import io.vectaury.cmp.consentstring.IABConstants;
import io.vectaury.cmp.consentstring.VectauryConsentString;

/**
 * Basic implementation of the IAB CMP
 */
public class SimpleCmp {

    private final SharedPreferences defaultSharedPreferences;
    private final VectauryConsentString consentString;

    public SimpleCmp(Context context) {
        defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        consentString = getConsentString();
    }

    private VectauryConsentString getConsentString() {
        return VectauryConsentString.load(defaultSharedPreferences);
    }

    @SuppressLint("ApplySharedPref")
    public void enableCmp() {
        defaultSharedPreferences
                .edit()
                .putBoolean(IABConstants.IAB_CMP_PRESENT, true)
                .putString(IABConstants.IAB_SUBJECT_TO_GDPR, "1")
                .commit();
    }

    public void setVendorConsent(int vendorId, boolean consent) {
        List<Integer> vendorsAllowed = consentString.getVendorsAllowed();
        if (consent) {
            vendorsAllowed.add(vendorId);
        } else {
            vendorsAllowed.remove((Integer) vendorId);
        }
        saveConsentString();
    }

    public boolean isVendorConsented(int vendorId) {
        return consentString.isVendorAllowed(vendorId);
    }

    public void setPurposeConsent(int purposeId, boolean consent) {
        List<Integer> purposesAllowed = consentString.getVendorPurposesAllowed();
        if (consent) {
            purposesAllowed.add(purposeId);
        } else {
            purposesAllowed.remove((Integer) purposeId);
        }
        saveConsentString();
    }

    public boolean isPurposeConsented(int purposeId) {
        return consentString.isVendorPurposeAllowed(purposeId);
    }

    @SuppressLint("ApplySharedPref")
    private void saveConsentString() {
        try {
            consentString.save(defaultSharedPreferences);
        } catch (ConsentStringParsingException ignored) {
        }
    }

}

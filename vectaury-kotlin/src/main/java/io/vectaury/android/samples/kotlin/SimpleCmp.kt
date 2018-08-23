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

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import io.vectaury.cmp.consentstring.IABConstants
import io.vectaury.cmp.consentstring.VectauryConsentString

/**
 * Basic implementation of the IAB CMP
 */
class SimpleCmp(context: Context) {

    private val defaultSharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val consentString: VectauryConsentString

    init {
        consentString = getVendorConsentString()
    }

    private fun getVendorConsentString(): VectauryConsentString {
        return VectauryConsentString.load(defaultSharedPreferences)
    }

    @SuppressLint("ApplySharedPref")
    fun enableCmp() {
        defaultSharedPreferences
                .edit()
                .putBoolean(IABConstants.IAB_CMP_PRESENT, true)
                .putString(IABConstants.IAB_SUBJECT_TO_GDPR, "1")
                .commit()
    }

    fun setVendorConsent(vendorId: Int, consent: Boolean) {
        val vendorsAllowed = consentString.vendorsAllowed
        if (consent) {
            vendorsAllowed.add(vendorId)
        } else {
            vendorsAllowed.remove(vendorId)
        }
        saveConsentString()
    }

    fun isVendorConsented(vendorId: Int): Boolean {
        return consentString.isVendorAllowed(vendorId)
    }

    fun setPurposeConsent(purposeId: Int, consent: Boolean) {
        val purposesAllowed = consentString.vendorPurposesAllowed
        if (consent) {
            purposesAllowed.add(purposeId)
        } else {
            purposesAllowed.remove(purposeId)
        }
        saveConsentString()
    }

    fun isPurposeConsented(purposeId: Int): Boolean {
        return consentString.isVendorPurposeAllowed(purposeId)
    }

    @SuppressLint("ApplySharedPref")
    private fun saveConsentString() {
        consentString.save(defaultSharedPreferences)
    }

}
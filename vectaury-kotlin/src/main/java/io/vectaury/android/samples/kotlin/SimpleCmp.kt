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
import io.vectaury.android.sdk.iab.IABConstants
import io.vectaury.cmp.consentstring.vendor.VendorConsentString
import io.vectaury.cmp.consentstring.vendor.VendorConsentStringParser
import java.util.*

/**
 * Basic implementation of the IAB CMP
 */
class SimpleCmp(context: Context) {

    private val defaultSharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val vendorConsentString: VendorConsentString

    private val defaultConsentString: VendorConsentString
        get() {
            val consent = VendorConsentString()
            consent.version = 1
            consent.created = Date()
            consent.lastUpdated = Date()
            consent.cmpId = 1
            consent.cmpVersion = 1
            consent.cmpConsentScreen = 1
            consent.cmpConsentLanguage = "fr"
            consent.vendorListVersion = 1
            return consent
        }

    init {
        vendorConsentString = getVendorConsentString()
    }

    private fun getVendorConsentString(): VendorConsentString {
        val consentString = defaultSharedPreferences.getString(IABConstants.IAB_CONSENT_STRING, null)
        if (consentString != null) {
            try {
                return VendorConsentStringParser.decode(consentString)
            } catch (e: Exception) {
                // ignored
            }
        }
        return defaultConsentString
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
        val vendorsAllowed = vendorConsentString.vendorsAllowed
        if (consent) {
            vendorsAllowed.add(vendorId)
        } else {
            vendorsAllowed.remove(vendorId)
        }
        saveConsentString()
    }

    fun isVendorConsented(vendorId: Int): Boolean {
        return vendorConsentString.isVendorAllowed(vendorId)
    }

    fun setPurposeConsent(purposeId: Int, consent: Boolean) {
        val purposesAllowed = vendorConsentString.purposesAllowed
        if (consent) {
            purposesAllowed.add(purposeId)
        } else {
            purposesAllowed.remove(purposeId)
        }
        saveConsentString()
    }

    fun isPurposeConsented(purposeId: Int): Boolean {
        return vendorConsentString.isPurposeAllowed(purposeId)
    }

    @SuppressLint("ApplySharedPref")
    private fun saveConsentString() {
        defaultSharedPreferences.edit().putString(IABConstants.IAB_CONSENT_STRING, VendorConsentStringParser.encode(vendorConsentString)).commit()
    }

}
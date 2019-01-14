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

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Switch
import android.widget.Toast
import io.vectaury.android.sdk.Vectaury
import io.vectaury.cmp.VectauryConsent
import io.vectaury.cmp.consentstring.IABConstants

class MainActivity : AppCompatActivity() {

    companion object {
        const val PERMISSION_REQUEST_LOCATION = 1
    }

    private val handler = Handler()
    private val updateOptin = object : Runnable {
        override fun run() {
            updateOptin()
            handler.postDelayed(this, 500)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.edit_consent).setOnClickListener { VectauryConsent.display(this) }

        if (CustomApplication.USE_VECTAURY_SDK_PERMISSIONS_FLOW) {
            Vectaury.get().startLocationPermissionWorkflow(this)
        } else {
            askLocationPermission()
        }

        handler.postDelayed(updateOptin, 500)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (VectauryConsent.isFromConsent(requestCode, resultCode, data)) {
            Toast.makeText(this, "CMP closed", Toast.LENGTH_SHORT).show()

            Log.i("VectauryConsent", "Consent String: " + PreferenceManager.getDefaultSharedPreferences(this).getString(IABConstants.IAB_CONSENT_STRING, null))
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun askLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Location permission granted", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Location permission denied", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun updateOptin() {
        val optinStatusToggle = findViewById<Switch>(R.id.optinToggle)
        optinStatusToggle.setOnCheckedChangeListener(null)
        optinStatusToggle.isChecked = Vectaury.get().isOptin
    }
}

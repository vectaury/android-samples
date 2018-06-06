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

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import android.widget.ToggleButton
import io.vectaury.android.sdk.Vectaury

class MainActivity : AppCompatActivity() {

    companion object {
        const val PERMISSION_REQUEST_LOCATION = 1
    }

    private lateinit var cmp: SimpleCmp

    private lateinit var internalOptinSwitch: Switch

    private lateinit var vectauryOptinSwitch: Switch
    private lateinit var purpose1OptinSwitch: Switch
    private lateinit var purpose2OptinSwitch: Switch
    private lateinit var purpose3OptinSwitch: Switch
    private lateinit var purpose4OptinSwitch: Switch
    private lateinit var purpose5OptinSwitch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cmp = SimpleCmp(this)
        cmp.enableCmp()

        if (CustomApplication.USE_VECTAURY_SDK_PERMISSIONS_FLOW) {
            Vectaury.get().startLocationPermissionWorkflow(this)
        } else {
            askLocationPermission()
        }

        internalOptinSwitch = findViewById(R.id.optinToggle)
        vectauryOptinSwitch = findViewById(R.id.sw_consent)
        purpose1OptinSwitch = findViewById(R.id.sw_purpose1)
        purpose2OptinSwitch = findViewById(R.id.sw_purpose2)
        purpose3OptinSwitch = findViewById(R.id.sw_purpose3)
        purpose4OptinSwitch = findViewById(R.id.sw_purpose4)
        purpose5OptinSwitch = findViewById(R.id.sw_purpose5)

        vectauryOptinSwitch.isChecked = cmp.isVendorConsented(Vectaury.IAB_VENDOR_ID)
        vectauryOptinSwitch.setOnCheckedChangeListener { _, isChecked ->
            cmp.setVendorConsent(Vectaury.IAB_VENDOR_ID, isChecked)
            updateInternalOptin()
        }

        purpose1OptinSwitch.isChecked = cmp.isPurposeConsented(1)
        purpose1OptinSwitch.setOnCheckedChangeListener { _, isChecked ->
            cmp.setPurposeConsent(1, isChecked)
            updateInternalOptin()
        }
        purpose2OptinSwitch.isChecked = cmp.isPurposeConsented(2)
        purpose2OptinSwitch.setOnCheckedChangeListener { _, isChecked ->
            cmp.setPurposeConsent(2, isChecked)
            updateInternalOptin()
        }
        purpose3OptinSwitch.isChecked = cmp.isPurposeConsented(3)
        purpose3OptinSwitch.setOnCheckedChangeListener { _, isChecked ->
            cmp.setPurposeConsent(3, isChecked)
            updateInternalOptin()
        }
        purpose4OptinSwitch.isChecked = cmp.isPurposeConsented(4)
        purpose4OptinSwitch.setOnCheckedChangeListener { _, isChecked ->
            cmp.setPurposeConsent(4, isChecked)
            updateInternalOptin()
        }
        purpose5OptinSwitch.isChecked = cmp.isPurposeConsented(5)
        purpose5OptinSwitch.setOnCheckedChangeListener { _, isChecked ->
            cmp.setPurposeConsent(5, isChecked)
            updateInternalOptin()
        }

        updateInternalOptin()
    }

    private fun updateInternalOptin() {
        Handler().postDelayed({
            internalOptinSwitch.setOnCheckedChangeListener(null)
            internalOptinSwitch.isChecked = Vectaury.get().isOptin
            internalOptinSwitch.setOnCheckedChangeListener { _, checked -> Vectaury.get().isOptin = checked }
        }, 500)
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
}

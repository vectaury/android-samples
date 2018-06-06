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

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import io.vectaury.android.sdk.Vectaury;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_LOCATION = 1;

    private SimpleCmp cmp;

    private Switch internalOptinSwitch;

    private Switch vectauryOptinSwitch;
    private Switch purpose1OptinSwitch;
    private Switch purpose2OptinSwitch;
    private Switch purpose3OptinSwitch;
    private Switch purpose4OptinSwitch;
    private Switch purpose5OptinSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cmp = new SimpleCmp(this);
        cmp.enableCmp();

        if (CustomApplication.USE_VECTAURY_SDK_PERMISSIONS_FLOW) {
            Vectaury.get().startLocationPermissionWorkflow(this);
        } else {
            askLocationPermission();
        }

        internalOptinSwitch = findViewById(R.id.optinToggle);
        vectauryOptinSwitch = findViewById(R.id.sw_consent);
        purpose1OptinSwitch = findViewById(R.id.sw_purpose1);
        purpose2OptinSwitch = findViewById(R.id.sw_purpose2);
        purpose3OptinSwitch = findViewById(R.id.sw_purpose3);
        purpose4OptinSwitch = findViewById(R.id.sw_purpose4);
        purpose5OptinSwitch = findViewById(R.id.sw_purpose5);

        vectauryOptinSwitch.setChecked(cmp.isVendorConsented(Vectaury.IAB_VENDOR_ID));
        vectauryOptinSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cmp.setVendorConsent(Vectaury.IAB_VENDOR_ID, isChecked);
                updateInternalOptin();
            }
        });

        purpose1OptinSwitch.setChecked(cmp.isPurposeConsented(1));
        purpose1OptinSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cmp.setPurposeConsent(1, isChecked);
                updateInternalOptin();
            }
        });
        purpose2OptinSwitch.setChecked(cmp.isPurposeConsented(2));
        purpose2OptinSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cmp.setPurposeConsent(2, isChecked);
                updateInternalOptin();
            }
        });
        purpose3OptinSwitch.setChecked(cmp.isPurposeConsented(3));
        purpose3OptinSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cmp.setPurposeConsent(3, isChecked);
                updateInternalOptin();
            }
        });
        purpose4OptinSwitch.setChecked(cmp.isPurposeConsented(4));
        purpose4OptinSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cmp.setPurposeConsent(4, isChecked);
                updateInternalOptin();
            }
        });
        purpose5OptinSwitch.setChecked(cmp.isPurposeConsented(5));
        purpose5OptinSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cmp.setPurposeConsent(5, isChecked);
                updateInternalOptin();
            }
        });

        updateInternalOptin();
    }

    private void updateInternalOptin() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                internalOptinSwitch.setOnCheckedChangeListener(null);
                internalOptinSwitch.setChecked(Vectaury.get().isOptin());
                internalOptinSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                        Vectaury.get().setOptin(checked);
                    }
                });
            }
        }, 500);
    }

    private void askLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Location permission granted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Location permission denied", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}

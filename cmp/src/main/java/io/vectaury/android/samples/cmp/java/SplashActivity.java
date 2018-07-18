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
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import io.vectaury.cmp.VectauryConsent;

public class SplashActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setTitle("CMP - Splash Screen");

        startProcessing();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (VectauryConsent.isFromConsent(requestCode, resultCode, data)) {
            Toast.makeText(this, "CMP closed", Toast.LENGTH_SHORT).show();
            // CMP UI is closed, finish remaining processing
            finishProcessing();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Simulate loading of data or anything else
     */
    private void startProcessing() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean uiWillStart = startVectauryCmp();
                if (!uiWillStart) {
                    finishProcessing();
                } else {
                    // CMP UI will start, wait that the CMP is closed to finish your processing
                }
            }
        }, 1500);
    }

    /**
     * Simulate loading of data or anything else
     */
    private void finishProcessing() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                redirectToMainActivity();
            }
        }, 1500);
    }

    private boolean startVectauryCmp() {
        return VectauryConsent.ask(this);
    }

    private void redirectToMainActivity() {
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);
        finish();
    }
}

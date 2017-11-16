package io.vectaury.android.samples.java;

import android.app.Application;

import io.vectaury.android.sdk.Tracker;

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Tracker.start(this, "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx");
    }
}

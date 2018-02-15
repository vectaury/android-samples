package io.vectaury.android.samples.java;

import android.app.Application;

import io.vectaury.android.sdk.Vectaury;

public class CustomApplication extends Application {

    public static final boolean USE_VECTAURY_SDK_PERMISSIONS_FLOW = false;

    @Override
    public void onCreate() {
        super.onCreate();

        Vectaury.start(this, "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx");
    }
}

package io.vectaury.android.samples.java;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import io.vectaury.android.sdk.Vectaury;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (CustomApplication.USE_VECTAURY_SDK_PERMISSIONS_FLOW) {
            Vectaury.get().startLocationPermissionWorkflow(this);
        } else {
            askLocationPermission();
        }

        ToggleButton optinStatusToggle = findViewById(R.id.optinToggle);
        optinStatusToggle.setChecked(Vectaury.get().isOptin());
        optinStatusToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                Vectaury.get().setOptin(checked);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Vectaury.get().displayOptinDialog(this);
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

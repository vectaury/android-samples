package io.vectaury.android.samples.kotlin

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import android.widget.ToggleButton
import io.vectaury.android.sdk.Vectaury

class MainActivity : AppCompatActivity() {

    companion object {
        const val PERMISSION_REQUEST_LOCATION = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (CustomApplication.USE_VECTAURY_SDK_PERMISSIONS_FLOW) {
            Vectaury.get().startLocationPermissionWorkflow(this)
        } else {
            askLocationPermission()
        }

        val optinStatusToggle: ToggleButton = findViewById(R.id.optinToggle)
        optinStatusToggle.isChecked = Vectaury.get().isOptin
        optinStatusToggle.setOnCheckedChangeListener({ _, checked ->
            Vectaury.get().isOptin = checked
        })
    }

    override fun onResume() {
        super.onResume()

        Vectaury.get().displayOptinDialog(this)
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

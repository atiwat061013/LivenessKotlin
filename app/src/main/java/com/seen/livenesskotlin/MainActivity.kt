package com.seen.livenesskotlin

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.huawei.hms.mlsdk.livenessdetection.MLLivenessCapture
import com.huawei.hms.mlsdk.livenessdetection.MLLivenessCaptureResult
import com.seen.livenesskotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA
    )
    private val RC_CAMERA_AND_EXTERNAL_STORAGE_CUSTOM = 0x01 shl 9
    private lateinit var livenessActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.customBtn.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            } else {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    PERMISSIONS,
                    RC_CAMERA_AND_EXTERNAL_STORAGE_CUSTOM
                )
            }
        }

        livenessActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                //Handle Data here
            }
        }

        val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startCustomActivity()
            }
        }

        requestPermission.launch(Manifest.permission.CAMERA)
    }

    private fun startCustomActivity() {
        val intent = Intent(this, LivenessCustomActivity::class.java)
        livenessActivityResultLauncher.launch(intent)
    }





}
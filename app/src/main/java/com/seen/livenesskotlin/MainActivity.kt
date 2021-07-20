package com.seen.livenesskotlin

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.customBtn.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                startCustomActivity()
            }
            ActivityCompat.requestPermissions(
                this@MainActivity,
                PERMISSIONS,
                RC_CAMERA_AND_EXTERNAL_STORAGE_CUSTOM
            )
        }


    }

    private fun startCustomActivity() {
        val intent = Intent(this, LivenessCustomActivity::class.java)
        this.startActivity(intent)
    }


    companion object {
        val customCallback : MLLivenessCapture.Callback = object : MLLivenessCapture.Callback {
            override fun onSuccess(result: MLLivenessCaptureResult) {
                Log.d("MainActivity", "result: $result")
            }

            override fun onFailure(errorCode: Int) {

            }
        }
    }





}
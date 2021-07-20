package com.seen.livenesskotlin.viewmodels

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seen.livenesskotlin.LivenessCustomActivity
import com.seen.livenesskotlin.MainActivity
import com.seen.mlkitliveness.`interface`.LivenessResult
import com.seen.mlkitliveness.model.Liveness

class LivenessViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val RC_CAMERA_AND_EXTERNAL_STORAGE_CUSTOM = 0x01 shl 9
    private  val liveness:Liveness
        get() {
            TODO()
        }

    val permissionRequest = MutableLiveData<String>()

    fun onPermissionResult(permission: String, granted: Boolean) {
        TODO("whatever you need to do")
    }



}
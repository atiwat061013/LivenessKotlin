package com.seen.livenesskotlin

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.huawei.hms.mlsdk.livenessdetection.MLLivenessCaptureResult
import com.huawei.hms.mlsdk.livenessdetection.MLLivenessDetectView
import com.huawei.hms.mlsdk.livenessdetection.OnMLLivenessDetectCallback

import com.seen.livenesskotlin.databinding.ActivityLivenessCustomBinding

class LivenessCustomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLivenessCustomBinding
    private lateinit var mlLivenessDetectView: MLLivenessDetectView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLivenessCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener(View.OnClickListener { finish() })

        val outMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(outMetrics)
        val widthPixels = outMetrics.widthPixels

        mlLivenessDetectView = MLLivenessDetectView.Builder()
            .setContext(this)
            .setOptions(MLLivenessDetectView.DETECT_MASK) // set Rect of face frame relative to surface in layout
            .setFaceFrameRect(Rect(0, 0, widthPixels, dip2px(this, 480f)))
            .setDetectCallback(object : OnMLLivenessDetectCallback {
                override fun onCompleted(result: MLLivenessCaptureResult) {
                    val resultIntent = Intent().apply {
                        bundleOf(
                            "isLive" to result.isLive,
                            "score" to result.score
                        )
                    }
                    setResult(RESULT_OK, resultIntent)

                    Log.d("LivenessCustom", "result: $result")
                    finish()
                }

                override fun onError(i: Int) {
//                        MainActivity.callback.onFailure(i)
//                    finish()
                }

                override fun onStateChange(i: Int, bundle: Bundle) {}
                override fun onInfo(i: Int, bundle: Bundle) {}
            }).build()

        binding.previewContainer.addView(mlLivenessDetectView)
        mlLivenessDetectView.onCreate(savedInstanceState)
    }

    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    override fun onDestroy() {
        super.onDestroy()
        mlLivenessDetectView.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        mlLivenessDetectView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mlLivenessDetectView.onResume()
    }
}
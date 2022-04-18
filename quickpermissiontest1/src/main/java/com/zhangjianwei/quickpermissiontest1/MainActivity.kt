package com.zhangjianwei.quickpermissiontest1

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zhangjianwei.quickpermissiontest1.databinding.ActivityMainBinding
import com.zjwdev.quickpermission.QuickPermission

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.makeRequestBtn.setOnClickListener {
            QuickPermission.init(this)
                .permissions(
                    Manifest.permission.CAMERA,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.RECORD_AUDIO
                )
                .explainReasonBeforeRequest()
                .onExplainRequestReason { scope, deniedList, beforeRequest ->
                    if (beforeRequest) { //当前是首次执行权限申请
                        scope.showRequestReasonDialog(deniedList, "为了保证程序正常工作，请您同意以下权限申请", "我已明白")
                    } else { //此前已执行过权限申请
                        val filteredList = deniedList.filter {
                            it == Manifest.permission.CAMERA
                        }
                        scope.showRequestReasonDialog(filteredList, "摄像机权限是程序必须依赖的权限", "我已明白")
                    }
                }
                .onForwardToSettings { scope, deniedList ->
                    val message = "Please allow following permissions in settings"
                    //val dialog = CustomDialogFragment(message, deniedList)
                    //scope.showForwardToSettingsDialog(dialog)
                }.request { allGranted, grantedList, deniedList ->
                    if (allGranted) { //所申请的权限都被授予
                        Toast.makeText(this, "All permissions are granted", Toast.LENGTH_SHORT)
                            .show()
                    } else { //所申请的权限未都被授予
                        Toast.makeText(
                            this,
                            "The following permissions are denied：$deniedList",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

        }
    }
}
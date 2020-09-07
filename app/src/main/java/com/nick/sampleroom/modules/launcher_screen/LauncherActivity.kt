package com.nick.sampleroom.modules.launcher_screen

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.nick.sampleroom.R
import com.nick.sampleroom.application.SampleRoomApplication
import com.nick.sampleroom.modules.main_activity.MainActivity
import com.nick.sampleroom.utils.base_class.BaseActivity

class LauncherActivity : BaseActivity() {

    private var launcherViewModel = LauncherViewModel(SampleRoomApplication.getInstance())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        initObservable()
    }

    private fun initObservable() {
        launcherViewModel.startMainActivity.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })
    }

    override fun onResume() {
        launcherViewModel.startMainActivity()
        super.onResume()
    }
}
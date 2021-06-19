package com.nick.sampleroom.modules.launcher_screen

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.nick.sampleroom.utils.base_classes.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LauncherViewModel(application: Application) : BaseViewModel(application) {

    companion object {
        private const val DELAY = 1500L
    }

    var startMainActivity = MutableLiveData<Boolean>()

    fun startMainActivity() {
        launch {
            delayProcess()
        }
    }

    private suspend fun delayProcess() {
        withContext(Dispatchers.Default) {
            delay(DELAY)
        }
        startMainActivity.value = true
    }
}
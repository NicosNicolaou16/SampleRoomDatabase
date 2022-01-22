package com.nick.sampleroom.utils.base_classes

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.MainThread
import androidx.annotation.UiThread
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.nick.sampleroom.R
import com.nick.sampleroom.database.init_database.MyRoomDatabase
import com.nick.sampleroom.databinding.RecommendedDefaultLoadingLayoutBinding

abstract class BaseActivity : AppCompatActivity() {

    private var loadingProgressBar: AlertDialog.Builder? = null
    private var loadingShown: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        MyRoomDatabase.destroyInstance()
        super.onDestroy()
    }

    override fun onPause() {
        stopLoading()
        super.onPause()
    }

    /**
     * this method uses to show the default progress bar (loading)
     * */
    @UiThread
    @MainThread
    protected fun startDefaultLoading() {
        stopLoading()
        val loadingView = RecommendedDefaultLoadingLayoutBinding.inflate(LayoutInflater.from(this))
        loadingProgressBar = AlertDialog.Builder(this, R.style.CustomAlertDialog).apply {
            setView(loadingView.root)
            setCancelable(false)
            create()
            loadingShown = show()
        }
    }

    @UiThread
    @MainThread
    protected fun stopLoading() {
        loadingShown?.apply { dismiss() }
    }
}
package com.nick.sampleroom.application

import android.app.Application
import com.nick.sampleroom.database.init_database.MyRoomDatabase

class SampleRoomApplication : Application() {

    companion object {
        private lateinit var instance: SampleRoomApplication

        @JvmStatic
        fun getInstance(): SampleRoomApplication = instance
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    /**
     * Room database instance
     * */
    fun getDatabase(): MyRoomDatabase = MyRoomDatabase(this)
}
package com.nick.sampleroom.application

import android.app.Application
import com.nick.sampleroom.database.init_database.MyRoomDatabase

object SampleRoomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    /**
     * Room database instance
     * */
    fun getDatabase(): MyRoomDatabase = MyRoomDatabase(this)
}
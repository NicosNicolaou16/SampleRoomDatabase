package com.nick.sampleroom.database.init_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nick.sampleroom.database.BookDao
import com.nick.sampleroom.database.BookModel
import com.nick.sampleroom.database.BookTypeDao
import com.nick.sampleroom.database.BookTypeModel
import com.nick.sampleroom.database.type_converter.TypeConverter

@Database(
        entities = [BookModel::class, BookTypeModel::class],
        version = 1,
        exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun bookTypeDao(): BookTypeDao

    companion object {
        @Volatile
        private var instance: MyRoomDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "DB_NAME"

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                MyRoomDatabase::class.java,
                DB_NAME
        ).build()

        fun destroyInstance() {
            instance = null
        }

        fun deleteAll() {
            instance?.clearAllTables()
        }
    }
}
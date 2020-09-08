package com.nick.sampleroom.database.models

import androidx.room.Dao
import androidx.room.Query
import com.nick.sampleroom.database.init_database.BaseDao

@Dao
interface BookDao : BaseDao<BookModel, MutableList<BookModel>> {

    @Query("SELECT * FROM bookmodel")
    suspend fun getAllBooks(): MutableList<BookModel>

    @Query("DELETE FROM bookmodel")
    suspend fun deleteAll()
}
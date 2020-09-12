package com.nick.sampleroom.database.models

import androidx.room.Dao
import androidx.room.Query
import com.nick.sampleroom.database.init_database.BaseDao

@Dao
interface BookTypeDao : BaseDao<BookGenreModel, MutableList<BookGenreModel>> {

    @Query("SELECT * FROM bookgenremodel")
    suspend fun getAllBookTypeList() : MutableList<BookGenreModel>

    @Query("DELETE FROM bookgenremodel")
    suspend fun deleteAll()
}
package com.nick.sampleroom.database.models

import androidx.room.Dao
import androidx.room.Query
import com.nick.sampleroom.database.init_database.BaseDao

@Dao
interface BookTypeDao : BaseDao<BookTypeModel, MutableList<BookTypeModel>> {

    @Query("SELECT * FROM booktypemodel")
    suspend fun getAllBookTypeList() : MutableList<BookTypeModel>

    @Query("DELETE FROM booktypemodel")
    suspend fun deleteAll()
}
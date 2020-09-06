package com.nick.sampleroom.database

import androidx.room.Dao
import com.nick.sampleroom.database.init_database.BaseDao

@Dao
interface BookAndBookTypeDao : BaseDao<BookAndBookTypeModel, MutableList<BookAndBookTypeModel>> {
}
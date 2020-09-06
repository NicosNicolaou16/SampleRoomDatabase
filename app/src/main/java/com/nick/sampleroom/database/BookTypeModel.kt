package com.nick.sampleroom.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nick.sampleroom.application.SampleRoomApplication
import kotlinx.coroutines.flow.flow

@Entity
data class BookTypeModel(
        @PrimaryKey
        var id: Long?,
        var type: String?
) {

    constructor() : this(-1L, "")

    companion object {

        suspend fun insertTheBookTypeData(bookTypeModel: BookTypeModel?) = with(SampleRoomApplication.getInstance()) {
            flow {
                bookTypeModel?.let { getDatabase().bookTypeDao().insertOrReplaceObject(it) }
                emit(bookTypeModel)
            }

        }
    }
}
package com.nick.sampleroom.database.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nick.sampleroom.application.SampleRoomApplication
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.flow.flow

@Parcelize
@Entity
data class BookTypeModel(
        @PrimaryKey
        var id: Long?,
        var type: String?
): Parcelable {

    constructor() : this(-1L, "")

    companion object {

        suspend fun insertAndSaveBookTypeData(bookTypeModel: BookTypeModel?) = with(SampleRoomApplication.getInstance()) {
            flow {
                bookTypeModel?.let { getDatabase().bookTypeDao().insertOrReplaceObject(it) }
                emit(bookTypeModel)
            }

        }
    }
}
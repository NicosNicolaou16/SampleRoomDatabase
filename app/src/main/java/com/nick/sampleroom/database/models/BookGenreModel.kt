package com.nick.sampleroom.database.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nick.sampleroom.application.SampleRoomApplication
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.flow.flow

@Parcelize
@Entity
data class BookGenreModel(
        @PrimaryKey
        var id: Long?,
        var genre: String?
): Parcelable {

    constructor() : this(-1L, "")

    companion object {

        suspend fun insertBookGenreData(bookGenreModel: BookGenreModel?) = with(SampleRoomApplication.getInstance()) {
            flow {
                bookGenreModel?.let { getDatabase().bookGenreDao().insertOrReplaceObject(it) }
                emit(bookGenreModel)
            }

        }
    }
}
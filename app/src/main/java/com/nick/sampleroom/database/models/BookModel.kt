package com.nick.sampleroom.database.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nick.sampleroom.application.SampleRoomApplication
import com.nick.sampleroom.database.type_converter.TypeConverter
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

@Parcelize
@Entity
data class BookModel(
        @PrimaryKey
        var id: Long?,
        var name: String?,
        var pages: Long?,
        @TypeConverters(TypeConverter::class)
        var bookTypeModel: BookTypeModel?,
        var bookTypeId: Long?
): Parcelable {

    constructor() : this(-1L, "", -1L, BookTypeModel(), -1L)

    companion object {

        suspend fun insertAndSaveBooks(bookModelList: MutableList<BookModel>) = with(SampleRoomApplication.getInstance()) {
            flow {
                //deleteTables()
                bookModelList.forEach {
                    insertAndSaveBookType(it)
                }
                getDatabase().bookDao().insertOrReplaceList(bookModelList)
                emit(getDatabase().bookDao().getAllBooks())
            }
        }

        private suspend fun insertAndSaveBookType(bookModel: BookModel) {
            BookTypeModel.insertAndSaveBookTypeData(bookModel.bookTypeModel).collect {
                bookModel.bookTypeId = it?.id
            }
        }

        private suspend fun deleteTables() = with(SampleRoomApplication.getInstance()) {
            getDatabase().bookDao().deleteAll()
            getDatabase().bookTypeDao().deleteAll()
        }
    }
}
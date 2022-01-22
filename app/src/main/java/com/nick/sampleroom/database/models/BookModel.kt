package com.nick.sampleroom.database.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nick.sampleroom.application.SampleRoomApplication
import com.nick.sampleroom.database.type_converter.TypeConverter
import kotlinx.coroutines.flow.flow
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class BookModel(
        @PrimaryKey
        var id: Long?,
        var name: String?,
        var pages: Long?,
        @TypeConverters(TypeConverter::class)
        var bookGenreModel: BookGenreModel?,
        var bookGenreId: Long?
): Parcelable {

    constructor() : this(-1L, "", -1L, BookGenreModel(), -1L)

    companion object {

        suspend fun insertBooks(bookModelList: MutableList<BookModel>) = with(SampleRoomApplication.getInstance()) {
            flow {
                //deleteTables()
                bookModelList.forEach {
                    insertBookGenre(it)
                }
                getDatabase().bookDao().insertOrReplaceList(bookModelList)
                emit(getDatabase().bookDao().getAllBooks())
            }
        }

        private suspend fun insertBookGenre(bookModel: BookModel) {
            BookGenreModel.insertBookGenreData(bookModel.bookGenreModel).collect {
                bookModel.bookGenreId = it?.id
            }
        }

        private suspend fun deleteTables() = with(SampleRoomApplication.getInstance()) {
            getDatabase().bookDao().deleteAll()
            getDatabase().bookGenreDao().deleteAll()
        }
    }
}
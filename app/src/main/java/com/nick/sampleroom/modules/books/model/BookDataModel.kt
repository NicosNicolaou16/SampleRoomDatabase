package com.nick.sampleroom.modules.books.model

import android.os.Parcelable
import com.nick.sampleroom.R
import com.nick.sampleroom.database.models.BookModel
import com.nick.sampleroom.modules.books.model.BookDataModel.BookViewGenre.BOOK_INFO
import com.nick.sampleroom.modules.books.model.BookDataModel.BookViewGenre.BOOK_GENRE
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookDataModel(
        var bookModel: BookModel?,
        var drawable: Int?,
        var bookViewGenre: BookViewGenre
) : Parcelable {

    enum class BookViewGenre {
        BOOK_GENRE,
        BOOK_INFO
    }

    companion object {

        fun createTheBookData(bookModelList: MutableList<BookModel>): MutableList<BookDataModel> {
            val bookDataModelList = mutableListOf<BookDataModel>()
            bookModelList.forEach {
                bookDataModelList.add(BookDataModel(it, null, BOOK_GENRE))
                bookDataModelList.add(BookDataModel(it, R.drawable.ic_baseline_library_books, BOOK_INFO))
            }
            return bookDataModelList
        }
    }
}
package com.nick.sampleroom.modules.books.model

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.nick.sampleroom.R
import com.nick.sampleroom.application.SampleRoomApplication
import com.nick.sampleroom.database.BookModel
import com.nick.sampleroom.modules.books.model.BookDataModel.BookViewType.*

data class BookDataModel(
        var bookModel: BookModel?,
        var drawable: Drawable?,
        var bookViewType: BookViewType
) {

    enum class BookViewType {
        BOOK_TYPE,
        BOOK_INFO
    }

    companion object {

        fun createTheBookData(bookModelList: MutableList<BookModel>): MutableList<BookDataModel> {
            val bookDataModelList = mutableListOf<BookDataModel>()
            bookModelList.forEach {
                bookDataModelList.add(BookDataModel(it, null, BOOK_TYPE))
                bookDataModelList.add(BookDataModel(it, ContextCompat.getDrawable(SampleRoomApplication.getInstance(), R.drawable.ic_baseline_library_books), BOOK_INFO))
            }
            return bookDataModelList
        }
    }
}
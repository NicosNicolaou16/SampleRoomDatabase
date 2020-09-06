package com.nick.sampleroom.modules.books.model

import com.nick.sampleroom.database.BookModel
import com.nick.sampleroom.database.BookTypeModel
import kotlin.random.Random

data class BookDataModel(var bookModel: BookModel) {

    companion object {

        fun createTheBookData(bookModelList: MutableList<BookModel>): MutableList<BookDataModel> {
            val bookDataModelList = mutableListOf<BookDataModel>()
            bookModelList.forEach {
                bookDataModelList.add(BookDataModel(it))
            }
            return bookDataModelList
        }
    }
}
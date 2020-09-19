package com.nick.sampleroom.database.models

import androidx.room.Embedded
import androidx.room.Relation

data class BookAndBookGenreModel(
        @Embedded
        var bookGenreModel: BookGenreModel,
        @Relation(
                parentColumn = "id",
                entityColumn = "bookGenreId"
        )
        var bookModel: BookModel
) {
}
package com.nick.sampleroom.database.models

import androidx.room.Embedded
import androidx.room.Relation

data class BookAndBookTypeModel(
        @Embedded
        var bookTypeModel: BookTypeModel,
        @Relation(
                parentColumn = "id",
                entityColumn = "bookTypeId"
        )
        var bookModel: BookModel
) {
}
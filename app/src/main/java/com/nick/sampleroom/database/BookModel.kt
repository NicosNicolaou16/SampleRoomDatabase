package com.nick.sampleroom.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookModel(
    @PrimaryKey
    var id: Long?,
    var name: String?
) {
}
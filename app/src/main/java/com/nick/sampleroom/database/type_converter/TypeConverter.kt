package com.nick.sampleroom.database.type_converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nick.sampleroom.database.models.BookGenreModel
import java.lang.reflect.Type

class TypeConverter {

    @TypeConverter
    fun fromString(value: String?): BookGenreModel {
        val listType: Type = object : TypeToken<BookGenreModel?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayLisr(list: BookGenreModel?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
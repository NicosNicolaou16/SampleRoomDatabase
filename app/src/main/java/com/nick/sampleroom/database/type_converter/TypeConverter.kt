package com.nick.sampleroom.database.type_converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nick.sampleroom.database.models.BookGenreModel
import java.lang.reflect.Type

class TypeConverter {

    @TypeConverter
    fun fromString(value: String?): BookGenreModel {
        //val listType: Type = TypeToken.getParameterized(BookGenreModel::class.java, BookGenreModel::class.java).type
        return Gson().fromJson(value,  object : TypeToken<BookGenreModel>() {}.type)
    }

    @TypeConverter
    fun fromObject(list: BookGenreModel?): String {
        return Gson().toJson(list)
    }
}
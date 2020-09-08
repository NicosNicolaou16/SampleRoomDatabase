package com.nick.sampleroom.database.type_converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nick.sampleroom.database.models.BookTypeModel
import java.lang.reflect.Type

class TypeConverter {

    @TypeConverter
    fun fromString(value: String?): BookTypeModel {
        val listType: Type = object : TypeToken<BookTypeModel?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayLisr(list: BookTypeModel?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
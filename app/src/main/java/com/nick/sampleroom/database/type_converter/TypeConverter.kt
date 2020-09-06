package com.nick.sampleroom.database.type_converter

import androidx.room.TypeConverter
import com.nick.sampleroom.database.BookTypeModel

class TypeConverter {

    @TypeConverter
    fun fromBookType(value: BookTypeModel?): String? {
        return if(value == null) null else value.type
    }

    @TypeConverter
    fun fromBookTypeList(value: String?): BookTypeModel? {
        return if(value == null) null else BookTypeModel()
    }
}
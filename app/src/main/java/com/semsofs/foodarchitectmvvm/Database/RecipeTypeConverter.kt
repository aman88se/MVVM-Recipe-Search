package com.semsofs.foodarchitectmvvm.Database

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class RecipeTypeConverter {

    @TypeConverter
    fun anyToString(attribute: Any?): String{
        if (attribute == null)
            return ""
        return attribute as String
    }

    @TypeConverter
    fun stringToAny(attribute: String?): Any{
        if (attribute == null)
            return ""
        return attribute
    }

}
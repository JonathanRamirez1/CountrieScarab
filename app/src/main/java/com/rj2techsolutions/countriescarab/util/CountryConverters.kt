package com.rj2techsolutions.countriescarab.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CountryConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromLanguagesMap(languages: Map<String, String>?): String? {
        return gson.toJson(languages)
    }

    @TypeConverter
    fun toLanguagesMap(json: String?): Map<String, String>? {
        return json?.let {
            val type = object : TypeToken<Map<String, String>>() {}.type
            gson.fromJson(it, type)
        }
    }
}


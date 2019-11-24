package com.pl.adambartosik.ligrettocalculator.model

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import com.pl.adambartosik.ligrettocalculator.model.tables.GameToPlayerToGameRound


class DataConverter {

    @TypeConverter
    fun fromCountryLangList(countryLang: List<GameToPlayerToGameRound>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<GameToPlayerToGameRound>>() {

        }.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toCountryLangList(countryLangString: String?): List<GameToPlayerToGameRound>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<GameToPlayerToGameRound>>() {

        }.type
        return gson.fromJson(countryLangString, type)
    }
}
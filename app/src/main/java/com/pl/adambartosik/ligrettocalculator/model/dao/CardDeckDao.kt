package com.pl.adambartosik.ligrettocalculator.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pl.adambartosik.ligrettocalculator.model.tables.CardDeck

@Dao
interface CardDeckDao {

    // Create

    @Insert
    fun insert(cardDeck: CardDeck)

    @Query("SELECT * FROM CardDeck")
    fun getAll(): LiveData<List<CardDeck>>
}
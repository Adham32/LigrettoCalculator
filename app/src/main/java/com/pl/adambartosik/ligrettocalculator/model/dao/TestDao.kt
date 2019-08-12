package com.pl.adambartosik.ligrettocalculator.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.pl.adambartosik.ligrettocalculator.model.tables.GameToPlayer

@Dao
interface TestDao {

    @Query( "SELECT * FROM GameToPlayer WHERE gameID = :idOfGame")
    fun getGTP(idOfGame: Int): LiveData<GameToPlayer>
}
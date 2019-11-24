package com.pl.adambartosik.ligrettocalculator.model.dao

import androidx.room.Dao
import androidx.room.Insert
import com.pl.adambartosik.ligrettocalculator.model.tables.GameRound

@Dao
interface GameRoundDao {

    @Insert
    fun insert(game: GameRound): Long
}
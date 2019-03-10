package com.pl.adambartosik.ligrettocalculator.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.pl.adambartosik.ligrettocalculator.model.tables.GameStatus

@Dao
interface GameStatusDao {

    // Create

    @Insert
    fun insert(gameStatus: GameStatus)


    // Read

    @Query("SELECT * FROM GameStatus")
    fun getAll(): LiveData<List<GameStatus>>


    // Delete

    @Delete
    fun delete(gameStatus: GameStatus)



}
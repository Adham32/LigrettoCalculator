package com.pl.adambartosik.ligrettocalculator.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pl.adambartosik.ligrettocalculator.model.entity.GameEntity
import com.pl.adambartosik.ligrettocalculator.model.tables.Game
import com.pl.adambartosik.ligrettocalculator.model.tables.GameStatus

@Dao
interface GameDao {

    // Create

    @Insert
    fun insert(game: Game): Long


    // Read

    @Query("SELECT * FROM Game ORDER BY updatedAtInMilis DESC")
    fun getAll(): LiveData<List<Game>>

    @Query("SELECT * FROM Game ORDER BY updatedAtInMilis DESC")
    fun getAllW(): LiveData<List<GameEntity>>

    @Query("SELECT * FROM Game")
    fun getAllOnce(): List<Game>

    @Query("SELECT * FROM Game WHERE g_id = :gameID")
    fun getGameByID(gameID: Int) : LiveData<Game?>


    // Delete

    @Delete
    fun delete(game: Game)


    // update
    @Update
    fun updateGame(currentGame: Game)


}
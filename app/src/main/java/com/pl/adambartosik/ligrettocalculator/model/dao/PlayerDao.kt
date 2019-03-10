package com.pl.adambartosik.ligrettocalculator.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.pl.adambartosik.ligrettocalculator.model.tables.Player

@Dao
interface PlayerDao {

    // Create

    @Insert
    fun insert(player: Player)


    // Read

    @Query("SELECT * FROM Player WHERE id = :id")
    fun getById(id: Int): LiveData<Player>



    // Delete

    @Query("DELETE FROM Player")
    fun deleteAll()

    @Query("DELETE FROM Player WHERE id = :id")
    fun deleteByID(id: Int)

    @Delete
    fun delete(player: Player)
}
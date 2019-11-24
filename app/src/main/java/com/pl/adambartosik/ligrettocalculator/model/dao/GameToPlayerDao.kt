package com.pl.adambartosik.ligrettocalculator.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pl.adambartosik.ligrettocalculator.model.entity.GameToPlayerEntity
import com.pl.adambartosik.ligrettocalculator.model.tables.GameToPlayer

@Dao
interface GameToPlayerDao {


    @Insert
    fun insert(gameToPlayer: GameToPlayer)

    @Query("SELECT * FROM GameToPlayer INNER JOIN GAME ON Game.g_id = gameID INNER JOIN Player ON Player.p_id = playerID INNER JOIN CardDeck ON CardDeck.cd_id = cardDeckID  WHERE gameID = :idOfGame")
    fun get(idOfGame : Int): LiveData<List<GameToPlayerEntity>>


    @Query("SELECT COUNT(*) FROM GameToPlayer WHERE gameID = :idOfGame ")
    fun getNumberOfPlayers(idOfGame: Int): LiveData<Int>
}
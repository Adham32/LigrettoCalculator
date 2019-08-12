package com.pl.adambartosik.ligrettocalculator.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.pl.adambartosik.ligrettocalculator.model.entity.GameAllPlayers
@Dao
interface GameToPlayerDao {


    @Query("SELECT * FROM GameToPlayer INNER JOIN GAME ON Game.id = gameID INNER JOIN Player ON Player.id = playerID INNER JOIN CardDeck ON CardDeck.id = cardDeckID WHERE Game.id = :idOfGame")
    fun get(idOfGame : Int): List<GameAllPlayers>
}
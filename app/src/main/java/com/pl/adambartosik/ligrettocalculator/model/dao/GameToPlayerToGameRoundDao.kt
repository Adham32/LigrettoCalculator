package com.pl.adambartosik.ligrettocalculator.model.dao

import androidx.room.Dao
import androidx.room.Insert
import com.pl.adambartosik.ligrettocalculator.model.tables.GameToPlayerToGameRound

@Dao
interface GameToPlayerToGameRoundDao {

    @Insert
    fun insert(gameToPlayerRound: GameToPlayerToGameRound)

/*    @Query("SELECT * FROM GameToPlayerToGameRound INNER JOIN GameToPlayer ON GameToPlayer.gtp_id = gameToPlayerID INNER JOIN GameRoundDao ON GameRoundDao.id = gameRoundID WHERE gtp_id = :idOfGame")
    fun get(idOfGame : Int): LiveData<List<GameToPlayerToGameRoundEntity>>*/

}
package com.pl.adambartosik.ligrettocalculator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pl.adambartosik.ligrettocalculator.model.entity.GameToPlayerToGameRoundEntity
import com.pl.adambartosik.ligrettocalculator.model.repository.GameToPlayerToGameRoundRepository
import com.pl.adambartosik.ligrettocalculator.model.tables.GameToPlayerToGameRound

class GameToPlayerToGameRoundViewModel(application: Application): AndroidViewModel(application) {

    private var repository: GameToPlayerToGameRoundRepository

    init {
        repository = GameToPlayerToGameRoundRepository(application)
    }

    fun insertNewGameToPlayerToRound(gameToPlayerID: Int, gameRoundID: Int, score: Int){
        repository.insert(GameToPlayerToGameRound(
            0,
            gameToPlayerID,
            gameRoundID,
            score,
            System.currentTimeMillis()
        ))
    }

    /*
    fun getGameToPlayerByGameID(gameId: Int): LiveData<List<GameToPlayerToGameRoundEntity>> {
        return repository.getAll(gameId)
    }*/
}
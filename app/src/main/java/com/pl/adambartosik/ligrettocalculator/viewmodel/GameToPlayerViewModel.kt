package com.pl.adambartosik.ligrettocalculator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pl.adambartosik.ligrettocalculator.model.entity.GameToPlayerEntity
import com.pl.adambartosik.ligrettocalculator.model.repository.GameToPlayerRepository
import com.pl.adambartosik.ligrettocalculator.model.tables.GameToPlayer

class GameToPlayerViewModel(application: Application): AndroidViewModel(application) {

    private var repository: GameToPlayerRepository

    init {
        repository = GameToPlayerRepository(application)
    }


    fun insertNewGameToPlayer(gameId: Int, playerID: Int, cardDeckID: Int){
        repository.insert(GameToPlayer(0, gameId, playerID, cardDeckID,System.currentTimeMillis()))
    }


    fun getGameToPlayerByGameID(gameId: Int): LiveData<List<GameToPlayerEntity>> {
        return repository.getAll(gameId)
    }
}
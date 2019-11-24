package com.pl.adambartosik.ligrettocalculator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.pl.adambartosik.ligrettocalculator.model.repository.GameRoundRepository
import com.pl.adambartosik.ligrettocalculator.model.tables.GameRound

class GameRoundViewModel(application: Application): AndroidViewModel(application) {


    private var repository: GameRoundRepository

    init {
        repository = GameRoundRepository(application)
    }

    fun insert(roundName: String, gameID : Int) {
        repository.insert(GameRound(
            0,
            roundName,
            gameID,
            System.currentTimeMillis()
        ))
    }

}
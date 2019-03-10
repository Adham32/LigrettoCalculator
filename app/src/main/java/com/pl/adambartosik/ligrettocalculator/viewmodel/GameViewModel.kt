package com.pl.adambartosik.ligrettocalculator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pl.adambartosik.ligrettocalculator.model.repository.GameRepository
import com.pl.adambartosik.ligrettocalculator.model.tables.Game

class GameViewModel(application: Application): AndroidViewModel(application) {

    var gamesArray: LiveData<List<Game>>
    private var repository: GameRepository

    init {
        repository =  GameRepository(application)
        gamesArray = repository.getAllGames()
    }


    fun insertNewGame(game: Game){
        repository.insert(game)
    }
}
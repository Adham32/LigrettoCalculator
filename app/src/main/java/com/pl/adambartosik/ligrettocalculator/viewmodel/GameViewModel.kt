package com.pl.adambartosik.ligrettocalculator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pl.adambartosik.ligrettocalculator.model.repository.GameRepository
import com.pl.adambartosik.ligrettocalculator.model.repository.GameStatusRepository
import com.pl.adambartosik.ligrettocalculator.model.tables.Game
import com.pl.adambartosik.ligrettocalculator.model.tables.GameStatus

class GameViewModel(application: Application): AndroidViewModel(application) {

    var gamesArray: LiveData<List<Game>>
    private var repository: GameRepository
    private var repositoryGS: GameStatusRepository

    init {
        repository = GameRepository(application)
        repositoryGS = GameStatusRepository(application)
        gamesArray = repository.getAllGames()
    }


    fun insertNewGame(name: String): Boolean {
        if(validationOfGameName(name)){
            repository.insert(Game(0, getCreateStatus().id, name, System.currentTimeMillis(),0))
            return true
        }else{
            return false
        }
    }

    private fun getCreateStatus(): GameStatus {
        return repositoryGS.getAll().value!!.first {
            it.name == "Created"
        }
    }

    private fun validationOfGameName(name: String): Boolean {
        return true
    }
}
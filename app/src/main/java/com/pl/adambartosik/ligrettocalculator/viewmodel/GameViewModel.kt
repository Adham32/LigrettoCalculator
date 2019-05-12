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
        return if(validationOfGameName(name)){
            // to good sort - update i the date as created
            repository.insert(Game(0, getCreateStatus().id, name, System.currentTimeMillis(),System.currentTimeMillis()))
            true
        }else{
            false
        }
    }

    fun deleteGame(game: Game){
        repository.delete(game)
    }


    // return created GameStatus from DB
    private fun getCreateStatus(): GameStatus {
        return repositoryGS.get("Created")
    }

    // checking name of game validation
    private fun validationOfGameName(name: String): Boolean {
        return name.isNotEmpty() && checkDuplicateNotExist(name)
    }

    private fun checkDuplicateNotExist(name: String): Boolean{
        return repository.getAllGamesOnce().find { it.name == name } == null
    }
}
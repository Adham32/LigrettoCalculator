package com.pl.adambartosik.ligrettocalculator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pl.adambartosik.ligrettocalculator.LigrettoCalculator
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.entity.GameEntity
import com.pl.adambartosik.ligrettocalculator.model.repository.GameRepository
import com.pl.adambartosik.ligrettocalculator.model.repository.GameStatusRepository
import com.pl.adambartosik.ligrettocalculator.model.tables.Game
import com.pl.adambartosik.ligrettocalculator.model.tables.GameStatus

class GameViewModel(application: Application): AndroidViewModel(application) {

    var gameStatus: LiveData<List<GameStatus>>
    var gamesArray: LiveData<List<Game>>
    var gamesEntityArray: LiveData<List<GameEntity>>
    private var repository: GameRepository
    private var repositoryGS: GameStatusRepository

    init {
        repository = GameRepository(application)
        repositoryGS = GameStatusRepository(application)
        gamesArray = repository.getAllGames()
        gameStatus = repositoryGS.getAll()
        gamesEntityArray = repository.getAllGamesW()
    }


    fun insertNewGame(): Long {
        return repository.insert(Game(0, getGameStatusByName(LigrettoCalculator.getContext().resources.getString(R.string.game_status_created)).id, "-", System.currentTimeMillis(),System.currentTimeMillis()))
    }

    fun insertNewGame(name: String): Boolean {
        return if(validationOfGameName(name)){
            // to good sort - update i the date as created
            repository.insert(Game(0, getGameStatusByName(LigrettoCalculator.getContext().resources.getString(R.string.game_status_created)).id, name, System.currentTimeMillis(),System.currentTimeMillis()))
            true
        }else{
            false
        }
    }

    fun deleteGame(game: Game){
        repository.delete(game)
    }


    fun getNumberOfPlayersByGameID(gameID: Int): LiveData<Int> {
        return repository.getPlayerListSizeByGameID(gameID)
    }


    /**
     * Retrun Asynchoricly Game Status Entity by name
     * @param name String
     * @return GameStatus
     */
    fun getGameStatusByName(name: String): GameStatus {
        return repositoryGS.get(name)
    }

    // checking name of game validation
    private fun validationOfGameName(name: String): Boolean {
        return name.isNotEmpty() && checkDuplicateNotExist(name)
    }

    private fun checkDuplicateNotExist(name: String): Boolean{
        return repository.getAllGamesOnce().find { it.name == name } == null
    }

    fun getGameByID(gameID: Int): LiveData<Game?> {
        return repository.getGameByID(gameID)
    }

    fun checkAvailableOfGameName(name: String): Boolean {
        return validationOfGameName(name)
    }

    fun update(game: Game) {
        repository.updateGame(game)
    }
}
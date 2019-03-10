package com.pl.adambartosik.ligrettocalculator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pl.adambartosik.ligrettocalculator.model.repository.GameStatusRepository
import com.pl.adambartosik.ligrettocalculator.model.tables.GameStatus

class StarterViewModel (application: Application): AndroidViewModel(application) {


    private var repository: GameStatusRepository

    var gameStatusArray: LiveData<List<GameStatus>>

    init {
        repository =  GameStatusRepository(application)
        gameStatusArray = repository.getAll()
    }

    private fun insertGameStatus(gameStatus: GameStatus){
        repository.insert(gameStatus)
    }



    fun generateStatus(){
        insertGameStatus(GameStatus("Created"))
        insertGameStatus(GameStatus("InProgress"))
        insertGameStatus(GameStatus("Ended"))
    }
}
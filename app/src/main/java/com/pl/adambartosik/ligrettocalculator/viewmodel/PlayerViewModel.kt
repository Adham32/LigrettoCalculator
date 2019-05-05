package com.pl.adambartosik.ligrettocalculator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pl.adambartosik.ligrettocalculator.model.repository.PlayerRepository
import com.pl.adambartosik.ligrettocalculator.model.tables.Player

class PlayerViewModel (application: Application): AndroidViewModel(application) {

    private var repository: PlayerRepository

    private var playersArray: LiveData<List<Player>>

    init {
        repository =  PlayerRepository(application)
        playersArray = repository.getAllPlayers()
    }


    fun insertNewPlayer(name: String): Boolean{
        if(validationOfPlayerName(name)){
            repository.insert(Player(0, name, System.currentTimeMillis()))
            return true
        }else{
            // TODO show validation errors
            return false
        }
    }

    private fun validationOfPlayerName(name: String): Boolean{
        return true
    }
}
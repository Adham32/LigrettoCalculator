package com.pl.adambartosik.ligrettocalculator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pl.adambartosik.ligrettocalculator.LigrettoCalculator
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.repository.CardDeckRepository
import com.pl.adambartosik.ligrettocalculator.model.repository.GameStatusRepository
import com.pl.adambartosik.ligrettocalculator.model.tables.CardDeck
import com.pl.adambartosik.ligrettocalculator.model.tables.GameStatus

class StarterViewModel (application: Application): AndroidViewModel(application) {


    private var repository: GameStatusRepository
    private var repositoryCD: CardDeckRepository

    var gameStatusArray: LiveData<List<GameStatus>>
    var cardDeckArray: LiveData<List<CardDeck>>

    init {
        repository =  GameStatusRepository(application)
        repositoryCD =  CardDeckRepository(application)
        gameStatusArray = repository.getAll()
        cardDeckArray = repositoryCD.getAll()
    }

    private fun insertGameStatus(gameStatus: GameStatus){
        repository.insert(gameStatus)
    }

    private fun insertCardDeck(cardDeck: CardDeck){
        repositoryCD.insert(cardDeck)
    }


    fun generateCardDeck(){
        insertCardDeck(CardDeck(LigrettoCalculator.getContext().resources.getString(R.string.cards_yellow), R.drawable.icon_card_yellow))
    }

    fun generateStatus(){
        insertGameStatus(GameStatus("Created"))
        insertGameStatus(GameStatus("InProgress"))
        insertGameStatus(GameStatus("Ended"))
    }
}
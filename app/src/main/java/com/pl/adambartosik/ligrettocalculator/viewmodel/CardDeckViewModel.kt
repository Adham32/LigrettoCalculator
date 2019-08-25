package com.pl.adambartosik.ligrettocalculator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pl.adambartosik.ligrettocalculator.model.repository.CardDeckRepository
import com.pl.adambartosik.ligrettocalculator.model.tables.CardDeck

class CardDeckViewModel(application: Application): AndroidViewModel(application) {


    fun getAllCardDeck(): LiveData<List<CardDeck>> {
        return cardDecList
    }


    private var cardDecList: LiveData<List<CardDeck>>
    private var repositoryCD: CardDeckRepository

    init {
        repositoryCD = CardDeckRepository(application)
        cardDecList = repositoryCD.getAll()
    }
}
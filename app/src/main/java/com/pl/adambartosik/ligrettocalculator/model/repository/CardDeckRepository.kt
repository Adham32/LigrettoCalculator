package com.pl.adambartosik.ligrettocalculator.model.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import com.pl.adambartosik.ligrettocalculator.LigrettoCalculator
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.LigrettoRoomDatabase
import com.pl.adambartosik.ligrettocalculator.model.dao.CardDeckDao
import com.pl.adambartosik.ligrettocalculator.model.tables.CardDeck
import com.pl.adambartosik.ligrettocalculator.model.tables.GameStatus

class CardDeckRepository {

    private lateinit var dao: CardDeckDao

    constructor(application: Application){
        var database = LigrettoRoomDatabase.getDatabase(application)
        dao = database.getCardDeckDao()
    }

    fun insert(cardDeck: CardDeck){
        InsertAsyncTask(dao).execute(cardDeck)
    }

    fun getAll(): LiveData<List<CardDeck>> {
        return dao.getAll()
    }

    class InsertAsyncTask(var dao: CardDeckDao) : AsyncTask<CardDeck, Void, Void>() {
        override fun doInBackground(vararg params: CardDeck): Void? {
            Log.d(LigrettoCalculator.getContext().resources.getString(R.string.log_card_deck_repo), "insertAsyncTask")
            dao.insert(params[0])
            return null
        }
    }
}
package com.pl.adambartosik.ligrettocalculator.model.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import com.pl.adambartosik.ligrettocalculator.LigrettoCalculator
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.LigrettoRoomDatabase
import com.pl.adambartosik.ligrettocalculator.model.dao.GameRoundDao
import com.pl.adambartosik.ligrettocalculator.model.tables.GameRound

class GameRoundRepository {

    private var dao: GameRoundDao

    constructor(application: Application){
        var database = LigrettoRoomDatabase.getDatabase(application)
        dao = database.getGameRoundDao()
    }

    fun insert(entity: GameRound){
        InsertAsyncTask(dao).execute(entity)
    }

    class InsertAsyncTask(var dao: GameRoundDao) : AsyncTask<GameRound, Void, Void>() {

        override fun doInBackground(vararg params: GameRound): Void? {
            Log.d(LigrettoCalculator.getContext().resources.getString(R.string.log_game_round_repo), "insertAsyncTask")
            dao.insert(params[0])
            return null
        }
    }
}
package com.pl.adambartosik.ligrettocalculator.model.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import com.pl.adambartosik.ligrettocalculator.LigrettoCalculator
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.LigrettoRoomDatabase
import com.pl.adambartosik.ligrettocalculator.model.dao.GameToPlayerDao
import com.pl.adambartosik.ligrettocalculator.model.entity.GameToPlayerEntity
import com.pl.adambartosik.ligrettocalculator.model.tables.GameToPlayer


class GameToPlayerRepository {

    private var dao: GameToPlayerDao

    constructor(application: Application){
        var database = LigrettoRoomDatabase.getDatabase(application)
        dao = database.getGameToPlayerDao()
    }

    fun getAll(gameID: Int): LiveData<List<GameToPlayerEntity>> {
        return dao.get(gameID)
    }

    fun insert(gameToPlayer: GameToPlayer) {
        InsertAsyncTask(dao).execute(gameToPlayer)
    }

    class InsertAsyncTask(var dao: GameToPlayerDao) : AsyncTask<GameToPlayer, Void, Void>()  {

        override fun doInBackground(vararg params: GameToPlayer): Void? {
            Log.d(LigrettoCalculator.getContext().resources.getString(R.string.log_game_repo), "Insert GameToPlayer AsyncTask")
            dao.insert(params[0])
            return null
        }

    }
}
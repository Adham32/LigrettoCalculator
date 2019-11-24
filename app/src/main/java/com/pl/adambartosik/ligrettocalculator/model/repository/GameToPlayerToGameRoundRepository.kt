package com.pl.adambartosik.ligrettocalculator.model.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import com.pl.adambartosik.ligrettocalculator.LigrettoCalculator
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.LigrettoRoomDatabase
import com.pl.adambartosik.ligrettocalculator.model.dao.GameToPlayerToGameRoundDao
import com.pl.adambartosik.ligrettocalculator.model.entity.GameToPlayerToGameRoundEntity
import com.pl.adambartosik.ligrettocalculator.model.tables.GameToPlayerToGameRound

class GameToPlayerToGameRoundRepository {

    private var dao: GameToPlayerToGameRoundDao

    constructor(application: Application){
        var database = LigrettoRoomDatabase.getDatabase(application)
        dao = database.getGameToPlayerToGameRoundDao()
    }

    fun insert(entity: GameToPlayerToGameRound){
        InsertAsyncTask(dao).execute(entity)
    }

   /* fun getAll(gameID: Int): LiveData<List<GameToPlayerToGameRoundEntity>> {
        return dao.get(gameID)
    }*/

    class InsertAsyncTask(var dao: GameToPlayerToGameRoundDao) : AsyncTask<GameToPlayerToGameRound, Void, Void>() {

        override fun doInBackground(vararg params: GameToPlayerToGameRound): Void? {
            Log.d(LigrettoCalculator.getContext().resources.getString(R.string.log_game_to_player_to_game_round_repo), "insertAsyncTask")
            dao.insert(params[0])
            return null
        }
    }
}
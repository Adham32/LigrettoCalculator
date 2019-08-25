package com.pl.adambartosik.ligrettocalculator.model.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import com.pl.adambartosik.ligrettocalculator.LigrettoCalculator
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.LigrettoRoomDatabase
import com.pl.adambartosik.ligrettocalculator.model.dao.GameDao
import com.pl.adambartosik.ligrettocalculator.model.dao.GameToPlayerDao
import com.pl.adambartosik.ligrettocalculator.model.entity.GameEntity
import com.pl.adambartosik.ligrettocalculator.model.tables.Game

class GameRepository {

    private var daoGTP: GameToPlayerDao
    private var dao: GameDao

    constructor(application: Application){
        var database = LigrettoRoomDatabase.getDatabase(application)
        dao = database.getGameDao()
        daoGTP = database.getGameToPlayerDao()
    }

    fun getAllGames(): LiveData<List<Game>>{
        return dao.getAll()
    }

    fun getAllGamesW(): LiveData<List<GameEntity>> {
        return dao.getAllW()
    }

    fun getPlayerListSizeByGameID(gameID: Int): LiveData<Int> {
        return daoGTP.getNumberOfPlayers(gameID)
    }

    fun getAllGamesOnce(): List<Game>{
        return GetAllAsyncTask(dao).execute().get()
    }

    fun insert(game: Game): Long{
        return InsertAsyncTask(dao).execute(game).get()
    }

    fun delete(game: Game) {
        DeleteGameAsyncTask(dao).execute(game)
    }

    fun getGameByID(gameID: Int): LiveData<Game?> {
        return dao.getGameByID(gameID)
    }

    fun updateGame(game: Game) {
        UpdateAsyncTask(dao).execute(game)
    }

    class InsertAsyncTask(var dao: GameDao) : AsyncTask<Game, Void, Long>() {
        override fun doInBackground(vararg params: Game): Long {
            Log.d(LigrettoCalculator.getContext().resources.getString(R.string.log_game_repo), "Insert Game AsyncTask")
            var id = dao.insert(params[0])
            return id
        }
    }

    class UpdateAsyncTask(var dao: GameDao) : AsyncTask<Game, Void, Void>() {
        override fun doInBackground(vararg params: Game): Void? {
            Log.d(LigrettoCalculator.getContext().resources.getString(R.string.log_game_repo), "Update Game AsyncTask")
            dao.updateGame(params[0])
            return null
        }
    }

    class GetAllAsyncTask(var dao: GameDao) : AsyncTask<Void, Void, List<Game>>() {
        override fun doInBackground(vararg params: Void?): List<Game> {
            Log.d(LigrettoCalculator.getContext().resources.getString(R.string.log_game_repo), "Get All Games AsyncTask")
            return dao.getAllOnce()
        }
    }

    class DeleteGameAsyncTask(var dao: GameDao) : AsyncTask<Game, Void, Void>() {
        override fun doInBackground(vararg params: Game): Void?{
            Log.d(LigrettoCalculator.getContext().resources.getString(R.string.log_game_repo), "Delete Game AsyncTask")
            dao.delete(params[0])
            return null
        }
    }
}
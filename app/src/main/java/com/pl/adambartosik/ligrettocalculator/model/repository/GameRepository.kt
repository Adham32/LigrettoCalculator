package com.pl.adambartosik.ligrettocalculator.model.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import com.pl.adambartosik.ligrettocalculator.model.LigrettoRoomDatabase
import com.pl.adambartosik.ligrettocalculator.model.dao.GameDao
import com.pl.adambartosik.ligrettocalculator.model.tables.Game

class GameRepository {

    private var dao: GameDao

    constructor(application: Application){
        var database = LigrettoRoomDatabase.getDatabase(application)
        dao = database.getGameDao()
    }

    fun getAllGames(): LiveData<List<Game>>{
        return dao.getAll()
    }

    fun getAllGamesOnce(): List<Game>{
        return GetAllAsyncTask(dao).execute().get()
    }

    fun insert(game: Game){
        InsertAsyncTask(dao).execute(game)
    }

    fun delete(game: Game) {
        DeleteGameAsyncTask(dao).execute(game)
    }

    class InsertAsyncTask(var dao: GameDao) : AsyncTask<Game, Void, Void>() {
        override fun doInBackground(vararg params: Game): Void? {
            Log.d("GameRepo", "insertAsyncTask")
            dao.insert(params[0])
            return null
        }
    }

    class GetAllAsyncTask(var dao: GameDao) : AsyncTask<Void, Void, List<Game>>() {
        override fun doInBackground(vararg params: Void?): List<Game> {
            Log.d("GameRepo", "GetAllAsyncTask")
            return dao.getAllOnce()
        }
    }

    class DeleteGameAsyncTask(var dao: GameDao) : AsyncTask<Game, Void, Void>() {
        override fun doInBackground(vararg params: Game): Void?{
            Log.d("GameRepo", "DeleteGameAsyncTask")
            dao.delete(params[0])
            return null
        }
    }
}
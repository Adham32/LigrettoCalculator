package com.pl.adambartosik.ligrettocalculator.model.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import com.pl.adambartosik.ligrettocalculator.model.LigrettoRoomDatabase
import com.pl.adambartosik.ligrettocalculator.model.dao.GameStatusDao
import com.pl.adambartosik.ligrettocalculator.model.tables.GameStatus

class GameStatusRepository {

    private var dao: GameStatusDao

    constructor(application: Application){
        var database = LigrettoRoomDatabase.getDatabase(application)
        dao = database.getGameStatusDao()
    }

    fun getAll(): LiveData<List<GameStatus>>{
        return dao.getAll()
    }

    fun insert(game: GameStatus){
        InsertAsyncTask(dao).execute(game)
    }

    class InsertAsyncTask(var dao: GameStatusDao) : AsyncTask<GameStatus, Void, Void>() {

        override fun doInBackground(vararg params: GameStatus): Void? {
            Log.d("GameStatusRepo", "insertAsyncTask")
            dao.insert(params[0])
            return null
        }

    }
}
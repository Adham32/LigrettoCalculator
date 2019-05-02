package com.pl.adambartosik.ligrettocalculator.model.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import com.pl.adambartosik.ligrettocalculator.model.LigrettoRoomDatabase
import com.pl.adambartosik.ligrettocalculator.model.dao.PlayerDao
import com.pl.adambartosik.ligrettocalculator.model.tables.Game
import com.pl.adambartosik.ligrettocalculator.model.tables.Player

class PlayerRepository {

    private var dao: PlayerDao

    constructor(application: Application){
        var database = LigrettoRoomDatabase.getDatabase(application)
        dao = database.getPlayerDao()
    }

    fun getPlayerEniity(id: Int): LiveData<Player>{
        return dao.getById(id)
    }
    fun getAllPlayers(): LiveData<List<Player>>{
        return dao.getAll()
    }

    fun insert(player: Player){
        InsertAsyncTask(dao).execute(player)
    }

    class InsertAsyncTask(var dao: PlayerDao) : AsyncTask<Player, Void, Void>() {

        override fun doInBackground(vararg params: Player): Void? {
            Log.d("PlayerRepo", "insertAsyncTask")
            dao.insert(params[0])
            return null
        }

    }

}
package com.pl.adambartosik.ligrettocalculator.model.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import com.pl.adambartosik.ligrettocalculator.LigrettoCalculator
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.LigrettoRoomDatabase
import com.pl.adambartosik.ligrettocalculator.model.dao.GameDao
import com.pl.adambartosik.ligrettocalculator.model.dao.PlayerDao
import com.pl.adambartosik.ligrettocalculator.model.tables.Game
import com.pl.adambartosik.ligrettocalculator.model.tables.Player

class PlayerRepository {

    private var dao: PlayerDao

    constructor(application: Application){
        var database = LigrettoRoomDatabase.getDatabase(application.applicationContext)
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

    fun delete(player: Player) {
        DeletePlayerAsyncTask(dao).execute(player)
    }

    class InsertAsyncTask(var dao: PlayerDao) : AsyncTask<Player, Void, Void>() {
        override fun doInBackground(vararg params: Player): Void? {
            Log.d(LigrettoCalculator().context!!.resources.getString(R.string.log_player_repo), "Insert Player AsyncTask")
            dao.insert(params[0])
            return null
        }
    }

    class DeletePlayerAsyncTask(var dao: PlayerDao) : AsyncTask<Player, Void, Void>() {
        override fun doInBackground(vararg params: Player): Void?{
            Log.d(LigrettoCalculator().context!!.resources.getString(R.string.log_player_repo), "Delete Player AsyncTask")
            dao.delete(params[0])
            return null
        }
    }
}
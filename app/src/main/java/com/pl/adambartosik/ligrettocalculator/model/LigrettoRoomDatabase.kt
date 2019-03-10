package com.pl.adambartosik.ligrettocalculator.model

import android.content.Context
import android.util.Log
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pl.adambartosik.ligrettocalculator.model.dao.GameDao
import com.pl.adambartosik.ligrettocalculator.model.dao.GameStatusDao
import com.pl.adambartosik.ligrettocalculator.model.dao.PlayerDao
import com.pl.adambartosik.ligrettocalculator.model.tables.Game
import com.pl.adambartosik.ligrettocalculator.model.tables.GameStatus
import com.pl.adambartosik.ligrettocalculator.model.tables.Player

@Database(entities = [Player::class,  GameStatus::class, Game::class], version = 1)
abstract class LigrettoRoomDatabase: RoomDatabase() {

    abstract fun getPlayerDao(): PlayerDao
    abstract fun getGameStatusDao(): GameStatusDao
    abstract fun getGameDao(): GameDao

    companion object {
        @Volatile
        private var INSTANCE: LigrettoRoomDatabase? = null

        fun getDatabase(context: Context): LigrettoRoomDatabase {
            if (INSTANCE == null) {
                synchronized(LigrettoRoomDatabase::class) {
                    Log.d("DB_ROOM", "getDatabase")
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        LigrettoRoomDatabase::class.java, "ligretto_database")
                        .addCallback(sRoomDatabaseCallback)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }

        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {
            override fun onCreate(@NonNull db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Log.d("DB_ROOM", "sRoomDatabaseCallback")
            }
        }
    }

}
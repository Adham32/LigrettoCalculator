package com.pl.adambartosik.ligrettocalculator.model

import android.content.Context
import android.util.Log
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pl.adambartosik.ligrettocalculator.model.dao.*
import com.pl.adambartosik.ligrettocalculator.model.tables.*

@Database(entities = [Player::class,  GameStatus::class, Game::class, GameToPlayer::class, CardDeck::class, GameRound::class], version = 5)
abstract class LigrettoRoomDatabase: RoomDatabase() {

    abstract fun getPlayerDao(): PlayerDao
    abstract fun getGameStatusDao(): GameStatusDao
    abstract fun getGameDao(): GameDao
    abstract fun getGameToPlayerDao(): GameToPlayerDao
    abstract fun getCardDeckDao(): CardDeckDao

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
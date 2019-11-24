package com.pl.adambartosik.ligrettocalculator.model.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "GameRound",
    foreignKeys = [
        ForeignKey(
            entity = Game::class,
            parentColumns = ["g_id"],
            childColumns = ["gameID"]
        )
])
class GameRound {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true)
    @NotNull
    var id: Int

    @NotNull
    var roundName: String

    @NotNull
    var gameID: Int

    @NotNull
    var createdAtInMillis: Long


    constructor(id: Int, roundName: String, gameID: Int, createdAtInMillis: Long) {
        this.id = id
        this.roundName = roundName
        this.gameID = gameID
        this.createdAtInMillis = createdAtInMillis
    }
}
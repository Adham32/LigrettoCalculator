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
    var roundName: Int

    @NotNull
    var gameID: Int

    @NotNull
    var createdAtInMilis: Long


    constructor(id: Int, roundName: Int, gameID: Int, createdAtInMilis: Long) {
        this.id = id
        this.roundName = roundName
        this.gameID = gameID
        this.createdAtInMilis = createdAtInMilis
    }
}
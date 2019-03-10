package com.pl.adambartosik.ligrettocalculator.model.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "GameRound",
    foreignKeys = [
        ForeignKey(
            entity = Game::class,
            parentColumns = ["id"],
            childColumns = ["gameID"]
        )
])
class GameRound {


    @PrimaryKey(autoGenerate = true)
    @NotNull
    private var id: Int

    @NotNull
    private var roundName: Int

    @NotNull
    private var gameID: Int

    @NotNull
    private var createdAtInMilis: Long


    constructor(id: Int, roundName: Int, gameID: Int, createdAtInMilis: Long) {
        this.id = id
        this.roundName = roundName
        this.gameID = gameID
        this.createdAtInMilis = createdAtInMilis
    }
}
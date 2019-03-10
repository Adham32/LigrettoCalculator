package com.pl.adambartosik.ligrettocalculator.model.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "GameToPlayerToGameToRound",
    foreignKeys = [
        ForeignKey(
            entity = GameToPlayer::class,
            parentColumns = ["id"],
            childColumns = ["gameToPlayerID"]
        ),
        ForeignKey(
            entity = GameRound::class,
            parentColumns = ["id"],
            childColumns = ["gameRoundID"]
        )
    ]
)

class GameToPlayerToGameToRound {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    private var id: Int

    @NotNull
    private var gameToPlayerID: Int

    @NotNull
    private var gameRoundID: Int

    @NotNull
    private var score: Int

    @NotNull
    private var createdAtInMilis: Long

    constructor(id: Int, gameToPlayerID: Int, gameRoundID: Int, score: Int, createdAtInMilis: Long) {
        this.id = id
        this.gameToPlayerID = gameToPlayerID
        this.gameRoundID = gameRoundID
        this.score = score
        this.createdAtInMilis = createdAtInMilis
    }
}
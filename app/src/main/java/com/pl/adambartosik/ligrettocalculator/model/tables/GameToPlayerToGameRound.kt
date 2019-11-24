package com.pl.adambartosik.ligrettocalculator.model.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "GameToPlayerToGameRound",
    foreignKeys = [
        ForeignKey(
            entity = GameToPlayer::class,
            parentColumns = ["gtp_id"],
            childColumns = ["gameToPlayerID"]
        ),
        ForeignKey(
            entity = GameRound::class,
            parentColumns = ["id"],
            childColumns = ["gameRoundID"]
        )
    ]
)

class GameToPlayerToGameRound {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true)
    @NotNull
    var id: Int

    @NotNull
    var gameToPlayerID: Int

    @NotNull
    var gameRoundID: Int

    @NotNull
    var score: Int

    @NotNull
    var createdAtInMillis: Long

    constructor(id: Int, gameToPlayerID: Int, gameRoundID: Int, score: Int, createdAtInMilis: Long) {
        this.id = id
        this.gameToPlayerID = gameToPlayerID
        this.gameRoundID = gameRoundID
        this.score = score
        this.createdAtInMillis = createdAtInMilis
    }
    constructor(
    ){
        this.id =0
        this.gameToPlayerID = 0
        this.gameRoundID = 0
        this.score = 0
        this.createdAtInMillis = 0L
    }
}
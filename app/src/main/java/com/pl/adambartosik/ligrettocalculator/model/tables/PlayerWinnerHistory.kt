package com.pl.adambartosik.ligrettocalculator.model.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "PlayerWinnerHistory",
    foreignKeys = [
        ForeignKey(
            entity = GameToPlayer::class,
            parentColumns = ["id"],
            childColumns = ["gameToPlayerID"]
        )
]
)
class PlayerWinnerHistory {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    private var id: Int

    @NotNull
    private var gameToPlayerID: Int

    @NotNull
    private var createdAtInMilis: Long

    constructor(id: Int, gameToPlayerID: Int, createdAtInMilis: Long) {
        this.id = id
        this.gameToPlayerID = gameToPlayerID
        this.createdAtInMilis = createdAtInMilis
    }
}
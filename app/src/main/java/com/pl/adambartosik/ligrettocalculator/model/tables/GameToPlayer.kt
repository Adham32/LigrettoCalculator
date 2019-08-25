package com.pl.adambartosik.ligrettocalculator.model.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "GameToPlayer",
    foreignKeys = [
        ForeignKey(
            entity = Game::class,
            parentColumns = ["g_id"],
            childColumns = ["gameID"]
        ),
        ForeignKey(
            entity = Player::class,
            parentColumns = ["p_id"],
            childColumns = ["playerID"]
        ),
        ForeignKey(
            entity = CardDeck::class,
            parentColumns = ["cd_id"],
            childColumns = ["cardDeckID"]
        )
    ]
)
class GameToPlayer {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "gtp_id", index = true)
    @NotNull
    var id: Int

    @NotNull
    var gameID: Int

    @NotNull
    var playerID: Int

    @NotNull
    var cardDeckID: Int

    @NotNull
    @ColumnInfo(name = "gtp_createdAtInMilis", index = true)
    var createdAtInMilis: Long

    constructor(id: Int, gameID: Int, playerID: Int, cardDeckID: Int, createdAtInMilis: Long) {
        this.id = id
        this.gameID = gameID
        this.playerID = playerID
        this.cardDeckID = cardDeckID
        this.createdAtInMilis = createdAtInMilis
    }



}
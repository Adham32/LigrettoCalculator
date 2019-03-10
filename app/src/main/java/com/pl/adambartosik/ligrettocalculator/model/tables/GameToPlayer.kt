package com.pl.adambartosik.ligrettocalculator.model.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "GameToPlayer",
    foreignKeys = [
        ForeignKey(
            entity = Game::class,
            parentColumns = ["id"],
            childColumns = ["gameID"]
        ),
        ForeignKey(
            entity = Player::class,
            parentColumns = ["id"],
            childColumns = ["playerID"]
        ),
        ForeignKey(
            entity = CardDeck::class,
            parentColumns = ["id"],
            childColumns = ["cardDeckID"]
        )
    ]
)
class GameToPlayer {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    private var id: Int

    @NotNull
    private var gameID: Int

    @NotNull
    private var playerID: Int

    @NotNull
    private var cardDeckID: Int

    @NotNull
    private var createdAtInMilis: Long

    constructor(id: Int, gameID: Int, playerID: Int, cardDeckID: Int, createdAtInMilis: Long) {
        this.id = id
        this.gameID = gameID
        this.playerID = playerID
        this.cardDeckID = cardDeckID
        this.createdAtInMilis = createdAtInMilis
    }
}
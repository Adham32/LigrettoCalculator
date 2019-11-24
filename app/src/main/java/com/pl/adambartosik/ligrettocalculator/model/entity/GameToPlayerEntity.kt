package com.pl.adambartosik.ligrettocalculator.model.entity

import androidx.room.Embedded
import androidx.room.Relation
import androidx.room.TypeConverters
import com.pl.adambartosik.ligrettocalculator.model.DataConverter
import com.pl.adambartosik.ligrettocalculator.model.tables.*

class GameToPlayerEntity {

    @Embedded
    var game: Game? = null

    @Embedded
    var player: Player? = null

    @Embedded
    var cardDeck: CardDeck? = null

    @Embedded
    var gameToPlayer: GameToPlayer? = null

//    @TypeConverters(DataConverter::class)
    @Relation(parentColumn = "gtp_id", entityColumn = "gameToPlayerID")
    var playerRoundsToGameToPlayer: List<GameToPlayerToGameRound>? = null

}
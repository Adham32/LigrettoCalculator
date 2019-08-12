package com.pl.adambartosik.ligrettocalculator.model.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.pl.adambartosik.ligrettocalculator.model.tables.CardDeck
import com.pl.adambartosik.ligrettocalculator.model.tables.Game
import com.pl.adambartosik.ligrettocalculator.model.tables.Player

class GameAllPlayers {

    @Embedded
    var game: Game? = null

    @Embedded
    var player: Player? = null

    @Embedded
    var cardDeck: CardDeck? = null

}
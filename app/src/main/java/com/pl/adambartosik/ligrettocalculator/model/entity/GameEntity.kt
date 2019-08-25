package com.pl.adambartosik.ligrettocalculator.model.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.pl.adambartosik.ligrettocalculator.model.tables.Game
import com.pl.adambartosik.ligrettocalculator.model.tables.GameRound
import com.pl.adambartosik.ligrettocalculator.model.tables.GameToPlayer

class GameEntity {

    @Embedded
    var game: Game? = null

    @Relation(parentColumn =  "g_id", entityColumn = "gameID")
    var gameToPlayerList: List<GameToPlayer>? = null

    @Relation(parentColumn =  "g_id", entityColumn = "gameID")
    var gameRoundList: List<GameRound>? = null

}
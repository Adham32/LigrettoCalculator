package com.pl.adambartosik.ligrettocalculator.model.entity

import androidx.room.Embedded
import com.pl.adambartosik.ligrettocalculator.model.tables.GameRound
import com.pl.adambartosik.ligrettocalculator.model.tables.GameToPlayerToGameRound

class GameToPlayerToGameRoundEntity {

    @Embedded
    var gameToPlayer: GameToPlayerEntity? = null

    @Embedded
    var gameRound: GameRound? = null

    @Embedded
    var entity: GameToPlayerToGameRound? = null
}
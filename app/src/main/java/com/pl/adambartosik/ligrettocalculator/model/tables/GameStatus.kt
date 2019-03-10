package com.pl.adambartosik.ligrettocalculator.model.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "GameStatus")
class GameStatus {

    @PrimaryKey(autoGenerate = true)
     var id: Int = 0

    @NotNull
    var name: String

    constructor( name: String) {
        this.name = name
    }



}
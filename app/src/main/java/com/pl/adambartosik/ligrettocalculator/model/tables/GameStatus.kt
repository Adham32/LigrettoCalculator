package com.pl.adambartosik.ligrettocalculator.model.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "GameStatus")
class GameStatus {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "gs_id", index = true)
     var id: Int = 0

    @NotNull
    @ColumnInfo(name = "gs_name", index = true)
    var name: String

    constructor( name: String) {
        this.name = name
    }



}
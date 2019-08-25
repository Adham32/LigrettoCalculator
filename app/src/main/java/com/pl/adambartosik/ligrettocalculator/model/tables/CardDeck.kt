package com.pl.adambartosik.ligrettocalculator.model.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "CardDeck")
class CardDeck {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cd_id", index = true)
    var id: Int = 0

    @ColumnInfo(name = "cd_name", index = true)
    @NotNull
    var name: String

    @NotNull
    var resID: Int


    constructor(name: String, resID: Int) {
        this.name = name
        this.resID = resID
    }
}
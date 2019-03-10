package com.pl.adambartosik.ligrettocalculator.model.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "CardDeck")
class CardDeck {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    private var id: Int

    @NotNull
    private var name: String

    @NotNull
    private var resID: Int


    constructor(id: Int, name: String, resID: Int) {
        this.id = id
        this.name = name
        this.resID = resID
    }
}
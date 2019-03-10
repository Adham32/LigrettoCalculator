package com.pl.adambartosik.ligrettocalculator.model.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Player")
class Player {

    @PrimaryKey(autoGenerate = true)
    @NotNull
     var id: Int

    @NotNull
     var name: String

    @NotNull
     var createdAtInMilis: Long


    constructor(id: Int, name: String, createdAtInMilis: Long) {
        this.id = id
        this.name = name
        this.createdAtInMilis = createdAtInMilis
    }



}
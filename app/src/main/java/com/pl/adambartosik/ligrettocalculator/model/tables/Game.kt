package com.pl.adambartosik.ligrettocalculator.model.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Game",
    foreignKeys = [
        ForeignKey(
            entity = GameStatus::class,
            parentColumns = ["id"],
            childColumns = ["statusID"]
        )
    ]
)

class Game {

    @PrimaryKey(autoGenerate = true)
    @NotNull
     var id: Int

    @NotNull
     var statusID: Int

    @NotNull
     var name: String

    @NotNull
     var createdAtInMilis: Long

     var updatedAtInMilis: Long

    constructor(id: Int, statusID: Int, name: String, createdAtInMilis: Long, updatedAtInMilis: Long) {
        this.id = id
        this.statusID = statusID
        this.name = name
        this.createdAtInMilis = createdAtInMilis
        this.updatedAtInMilis = updatedAtInMilis
    }
}
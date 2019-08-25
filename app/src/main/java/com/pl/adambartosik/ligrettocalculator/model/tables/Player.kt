package com.pl.adambartosik.ligrettocalculator.model.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Player")
class Player {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "p_id", index = true)
    @NotNull
     var id: Int

    @NotNull
    @ColumnInfo(name = "p_name", index = true)
     var name: String

    @NotNull
    @ColumnInfo(name = "p_createdAtInMilis", index = true)
     var createdAtInMilis: Long


    constructor(id: Int, name: String, createdAtInMilis: Long) {
        this.id = id
        this.name = name
        this.createdAtInMilis = createdAtInMilis
    }


}
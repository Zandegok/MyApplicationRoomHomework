package com.example.myapplicationroomhomework.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "records")
data class Record(
    var player: String = "",
    var score: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}

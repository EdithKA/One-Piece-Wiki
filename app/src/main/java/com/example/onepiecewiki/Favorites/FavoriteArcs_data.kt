package com.example.onepiecewiki.Favorites

import androidx.room.ColumnInfo
import androidx.room.Entity


//Lista donde se añadirán los arcos que el usuario marque como favoritos
@Entity(tableName = "FavoriteArcs_data", primaryKeys = ["id"])
data class FavoriteArcs_data(
    @ColumnInfo(name = "id") val id: Int,
    val arc_number: String,
    @ColumnInfo(name = "arc_title") val arc_title: String,
    val arc_chapters: String,
    val arc_volume: String,
    val arc_episode: String
)
package com.example.onepiecewiki.Favorites

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "FavoriteCharacter_data", primaryKeys = ["id"])
data class FavoriteCharacter_data(
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "french_name") val frenchName: String,
    val job: String,
    val size: String,
    val birthday: String,
    val age: String,
    val bounty: String,
    val status: String,
    @ColumnInfo(name = "crew_id") val crewId: Int?,
    @ColumnInfo(name = "fruit_id") val fruitId: Int?,
    @ColumnInfo(name = "2nd_fruit_id") val secondFruitId: Int?
)

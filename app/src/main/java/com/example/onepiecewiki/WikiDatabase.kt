package com.example.onepiecewiki

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.onepiecewiki.Arcs.Arc_data
import com.example.onepiecewiki.Favorites.FavoriteArcs_data
import com.example.onepiecewiki.Characters.Character_data
import com.example.onepiecewiki.Crews.Crew_data
import com.example.onepiecewiki.Favorites.FavoriteCharacter_data


@Database(
    entities = [Arc_data::class, Character_data::class, FavoriteCharacter_data::class, FavoriteArcs_data::class, Crew_data::class],
    version = 10,
    exportSchema = false
)
abstract class WikiDatabase : RoomDatabase() {
    abstract val dao: DAO

    companion object {
        @Volatile
        private var INSTANCE: WikiDatabase? = null

        fun getInstance(context: Context): WikiDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WikiDatabase::class.java,
                    "wiki_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
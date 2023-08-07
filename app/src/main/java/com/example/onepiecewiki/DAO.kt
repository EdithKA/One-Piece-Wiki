package com.example.onepiecewiki

import androidx.room.*
import com.example.onepiecewiki.Arcs.Arc_data
import com.example.onepiecewiki.Favorites.FavoriteArcs_data
import com.example.onepiecewiki.Characters.Character_data
import com.example.onepiecewiki.Favorites.FavoriteCharacter_data
import com.example.onepiecewiki.Crews.Crew_data

@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllArcs(arcs: List<Arc_data>)

    @Query("SELECT * FROM Arc_data")
    fun getAllArcs(): List<Arc_data>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllCharacters(characters: List<Character_data>)

    @Query("SELECT * FROM Character_data")
    fun getAllCharacters(): List<Character_data>


    //Personajes
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacter(character: Character_data)

    @Update
    fun updateCharacter(character: Character_data)

    @Query("SELECT EXISTS(SELECT 1 FROM Character_data WHERE french_name = :name LIMIT 1)")
    suspend fun checkCharacterExists(name: String): Boolean

    @Query("SELECT MAX(id) FROM Character_data")
    suspend fun getLastCharacterId(): Int?

    @Delete
    suspend fun deleteCharacter(character: Character_data)

    //Arcos

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArc(arc: Arc_data)

    @Update
    fun updateArc(arc: Arc_data)

    @Query("SELECT EXISTS(SELECT 1 FROM Arc_data WHERE arc_title = :name LIMIT 1)")
    suspend fun checkArcExist(name: String): Boolean

    @Query("SELECT MAX(id) FROM Arc_data")
    suspend fun getLastArc(): Int?

    @Delete
    suspend fun deleteArc(arc: Arc_data)

    //Favoritos
    //Personajes
    @Query("SELECT * FROM FavoriteCharacter_data")
    suspend fun getAllFavoriteCharacters(): List<FavoriteCharacter_data>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCharacter(character: FavoriteCharacter_data)

    @Delete
    suspend fun deleteFavoriteCharacter(character: FavoriteCharacter_data)

    @Query("SELECT EXISTS(SELECT 1 FROM FavoriteCharacter_data WHERE french_name = :name LIMIT 1)")
    suspend fun checkFavoriteCharacterExists(name: String): Boolean

    //Arcos
    @Query("SELECT * FROM FavoriteArcs_data")
    suspend fun getAllFavoriteArcs(): List<FavoriteArcs_data>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteArcs(arcs: FavoriteArcs_data)

    @Delete
    suspend fun deleteFavoriteArcs(arcs: FavoriteArcs_data)

    @Query("SELECT EXISTS(SELECT 1 FROM FavoriteArcs_data WHERE arc_title = :name LIMIT 1)")
    suspend fun checkFavoriteArcsExists(name: String): Boolean



    //Tripulaciones
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllCrews(arcs: List<Crew_data>)

    @Query("SELECT * FROM Crew_data")
    fun getAllCrews(): List<Crew_data>

    @Query("SELECT * FROM character_data WHERE crew_id = :crewId")
    suspend fun getCrewMembers(crewId: String): List<Character_data>

    @Query("SELECT COUNT(*) FROM Character_data WHERE crew_id = :crewId")
    suspend fun getCrewsMembersCount(crewId: String): Int


    @Query("SELECT * FROM Character_data WHERE french_name LIKE :name || '%' ORDER BY id")
    fun getCharacterByName(name: String): List<Character_data>

    @Query("SELECT * FROM Crew_data WHERE id LIKE :crew_id")
    fun getCrewById(crew_id: Int): Crew_data


}

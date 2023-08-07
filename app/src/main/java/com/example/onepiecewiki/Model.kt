package com.example.onepiecewiki

import android.content.Context
import com.android.volley.Response
import com.example.onepiecewiki.Arcs.Arc_data
import com.example.onepiecewiki.Favorites.FavoriteArcs_data

import com.example.onepiecewiki.Characters.Character_data
import com.example.onepiecewiki.Crews.Crew_data
import com.example.onepiecewiki.Favorites.FavoriteCharacter_data
import kotlinx.coroutines.*

class Model(private val context: Context) {
    private val database = WikiDatabase.getInstance(context)
    private val network: Network = Network.getInstance(context)

    @OptIn(DelicateCoroutinesApi::class)
    fun getAllArcs(
        listener: (List<Arc_data>) -> Unit,
        errorListener: (Throwable) -> Unit
    ) = CoroutineScope(Dispatchers.Main).launch {
        val arcDataList = withContext(Dispatchers.IO) {
            database.dao.getAllArcs()
        }
        if (arcDataList.isEmpty()) {
            try {
                network.getArcs(
                    listener = { fetchedArcDataList ->
                        GlobalScope.launch {
                            database.dao.insertAllArcs(fetchedArcDataList)
                        }
                        listener(fetchedArcDataList)
                    },
                    errorListener = { error ->
                        errorListener(error)
                    }
                )
            } catch (error: Exception) {
                errorListener(error)
            }
        } else {
            listener(arcDataList)
        }
    }

    fun getAllCharacters(
        listener: (List<Character_data>) -> Unit,
        errorListener: (Throwable) -> Unit
    ) = CoroutineScope(Dispatchers.Main).launch {
        val characterDataList = withContext(Dispatchers.IO) {
            database.dao.getAllCharacters()
        }
        if (characterDataList.isEmpty()) {
            try {
                network.getAllCharacters(
                    listener = { fetchedCharacterDataList ->
                        GlobalScope.launch {
                            database.dao.insertAllCharacters(fetchedCharacterDataList)
                        }
                        listener(fetchedCharacterDataList)
                    },
                    errorListener = { error ->
                        errorListener(error)
                    }
                )
            } catch (error: Exception) {
                errorListener(error)
            }
        } else {
            listener(characterDataList)
        }
    }

    fun updateCharacter(character: Character_data, callback: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            database.dao.updateCharacter(character)
            callback()
        }
    }

    fun updateArc(arc: Arc_data, callback: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            database.dao.updateArc(arc)
            callback()
        }
    }

    fun deleteCharacter(character: Character_data, callback: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            database.dao.deleteCharacter(character)
            callback()
        }
    }
    fun deleteArc(arc: Arc_data, callback: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            database.dao.deleteArc(arc)
            callback()
        }
    }

    fun insertCharacter(character: Character_data, callback: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            database.dao.insertCharacter(character)
            callback()
        }
    }

    fun insertArc(arc: Arc_data, callback: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            database.dao.insertArc(arc)
            callback()
        }
    }

    fun getAllFavoriteCharacters(
        listener: (List<FavoriteCharacter_data>) -> Unit,
        errorListener: (Throwable) -> Unit
    ) = CoroutineScope(Dispatchers.Main).launch {
        val favoriteCharacterDataList = withContext(Dispatchers.IO) {
            database.dao.getAllFavoriteCharacters()
        }

        val characterDataList = withContext(Dispatchers.IO) {
            database.dao.getAllCharacters()
        }

        // Comprobar y eliminar personajes favoritos inexistentes
        val validFavoriteCharacters = mutableListOf<FavoriteCharacter_data>()
        for (favoriteCharacter in favoriteCharacterDataList) {
            val characterExists = characterDataList.any { it.frenchName == favoriteCharacter.frenchName }
            if (characterExists) {
                validFavoriteCharacters.add(favoriteCharacter)
            } else {
                deleteFavoriteCharacter(favoriteCharacter) //Eliminamos el personaje de la lista de favoritos
            }
        }

        listener(validFavoriteCharacters)
    }

    fun insertFavoriteCharacter(character: FavoriteCharacter_data) {
        CoroutineScope(Dispatchers.IO).launch {
            database.dao.insertFavoriteCharacter(character)
        }
    }

    fun deleteFavoriteCharacter(character: FavoriteCharacter_data) {
        CoroutineScope(Dispatchers.IO).launch {
            database.dao.deleteFavoriteCharacter(character)
        }
    }

    fun getAllFavoriteArcs(
        listener: (List<FavoriteArcs_data>) -> Unit,
        errorListener: (Throwable) -> Unit
    ) = CoroutineScope(Dispatchers.Main).launch {
        val favoriteArcDataList = withContext(Dispatchers.IO) {
            database.dao.getAllFavoriteArcs()
        }

        val arcDataList = withContext(Dispatchers.IO) {
            database.dao.getAllArcs()
        }

        // Comprobar y eliminar arcos favoritos inexistentes
        val validFavoriteArcs = mutableListOf<FavoriteArcs_data>()
        for (favoriteArc in favoriteArcDataList) {
            val arcExists = arcDataList.any { it.arc_title == favoriteArc.arc_title }
            if (arcExists) {
                validFavoriteArcs.add(favoriteArc)
            } else {
                deleteFavoriteArc(favoriteArc) // Método para eliminar el arco favorito
            }
        }

        listener(validFavoriteArcs)
    }

    fun insertFavoriteArc(arc: FavoriteArcs_data) {
        CoroutineScope(Dispatchers.IO).launch {
            database.dao.insertFavoriteArcs(arc)
        }
    }

    fun deleteFavoriteArc(arc: FavoriteArcs_data) {
        CoroutineScope(Dispatchers.IO).launch {
            database.dao.deleteFavoriteArcs(arc)
        }
    }

    fun getAllCrews(
        listener: (List<Crew_data>) -> Unit,
        errorListener: (Throwable) -> Unit
    ) = CoroutineScope(Dispatchers.Main).launch {
        val crewDataList = withContext(Dispatchers.IO) {
            database.dao.getAllCrews()
        }
        if (crewDataList.isEmpty()) {
            try {
                network.getAllCrews(
                    listener = { fetchedCrewDataList ->
                        GlobalScope.launch {
                            database.dao.insertAllCrews(fetchedCrewDataList)

                        }
                        listener(fetchedCrewDataList)
                    },
                    errorListener = { error ->
                        errorListener(error)
                    }
                )
            } catch (error: Exception) {
                errorListener(error)
            }
        } else {

            listener(crewDataList)
        }
    }

    fun getAllMembers(
        crew_id: Int,
        listener: (List<Character_data>) -> Unit,
        errorListener: (Throwable) -> Unit
    ) = CoroutineScope(Dispatchers.Main).launch {
        val characterDataList = withContext(Dispatchers.IO) {
            database.dao.getCrewMembers(crew_id.toString())
        }
        if (characterDataList.isEmpty()) {
            try {
                network.getAllMembers(
                    crewId = crew_id.toString(),
                    listener = { fetchedCharacterDataList ->
                        GlobalScope.launch {
                            database.dao.insertAllCharacters(fetchedCharacterDataList)
                        }
                        listener(fetchedCharacterDataList)
                    },
                    errorListener = { error ->
                        errorListener(error)
                    }
                )
            } catch (error: Exception) {
                errorListener(error)
            }
        } else {
            listener(characterDataList)
        }
    }

    suspend fun checkCharacterExist(name: String): Boolean = withContext(Dispatchers.IO) {
        return@withContext database.dao.checkCharacterExists(name)
    }
    suspend fun checkArcExist(name: String): Boolean = withContext(Dispatchers.IO) {
        return@withContext database.dao.checkArcExist(name)
    }

    suspend fun checkFavoriteArcsExists(name: String): Boolean = withContext(Dispatchers.IO) {
        return@withContext database.dao.checkFavoriteArcsExists(name)
    }

    suspend fun checkFavoriteCharacterExists(name: String): Boolean = withContext(Dispatchers.IO) {
        return@withContext database.dao.checkFavoriteCharacterExists(name)
    }

    fun searchCharacterName(name: String,
                                 listener: Response.Listener<List<Character_data>>)= GlobalScope.launch(Dispatchers.Main){
        val characters = withContext(Dispatchers.IO) {
            database.dao.getCharacterByName(name)
        }
        listener.onResponse(characters)

    }

    fun getCrewById(crewId: Int, listener: Response.Listener<Crew_data>) = GlobalScope.launch(Dispatchers.Main) {
        val crew = withContext(Dispatchers.IO) {
            database.dao.getCrewById(crewId)
        }
        listener.onResponse(crew)
    }





}






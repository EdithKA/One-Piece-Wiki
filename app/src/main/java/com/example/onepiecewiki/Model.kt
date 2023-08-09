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

    @OptIn(DelicateCoroutinesApi::class)
    fun getAllFavoriteArcs(
        listener: (List<Arc_data>) -> Unit,
        errorListener: (Throwable) -> Unit
    ) = CoroutineScope(Dispatchers.Main).launch {
        val favoriteArcDataList = withContext(Dispatchers.IO) {
            database.dao.getAllFavoriteArcs()
        }
        if (favoriteArcDataList.isEmpty()) {
            // Manejar el caso de que no haya arcos favoritos
            listener(emptyList())
        } else {
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
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getAllFavoriteCharacters(
        listener: (List<Character_data>) -> Unit,
        errorListener: (Throwable) -> Unit
    ) = CoroutineScope(Dispatchers.Main).launch {
        val favoriteCharacterDataList = withContext(Dispatchers.IO) {
            database.dao.getAllFavoriteCharacters()
        }
        if (favoriteCharacterDataList.isEmpty()) {
            // Manejar el caso de que no haya personajes favoritos
            listener(emptyList())
        } else {
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






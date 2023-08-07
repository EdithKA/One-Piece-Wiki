package com.example.onepiecewiki

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.onepiecewiki.Arcs.Arc_data
import com.example.onepiecewiki.Characters.Character_data
import com.example.onepiecewiki.Crews.Crew_data
import org.json.JSONArray
import org.json.JSONException

class Network private constructor(context: Context) {
    companion object : SingletonHolder<Network, Context>(::Network)

    private val queue: RequestQueue = Volley.newRequestQueue(context)
    private val urlArcos = "https://api.api-onepiece.com/sagas"
    private val urlCharacters = "https://api.api-onepiece.com/characters"
    private val urlCrews = "https://api.api-onepiece.com/crews"

    fun getArcs(
        listener: (List<Arc_data>) -> Unit,
        errorListener: (VolleyError) -> Unit
    ) {
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            urlArcos,
            null,
            { response: JSONArray ->
                try {
                    val arcs = mutableListOf<Arc_data>()
                    for (i in 0 until response.length()) {
                        val saga = response.getJSONObject(i)
                        val id = saga.getInt("id")
                        val arcNumber = saga.getString("saga_number")
                        val arcTitle = saga.getString("saga_title")
                        val arcChapters = saga.getString("saga_chapitre")
                        val arcVolume = saga.getString("saga_volume")
                        val arcEpisode = saga.getString("saga_episode")

                        val arcData = Arc_data(
                            id,
                            arcNumber,
                            arcTitle,
                            arcChapters,
                            arcVolume,
                            arcEpisode
                        )
                        arcs.add(arcData)
                    }
                    listener(arcs)
                } catch (e: JSONException) {
                    errorListener(VolleyError(e.message))
                }
            },
            { error: VolleyError? ->
                error?.let { errorListener(it) }
            }
        )

        queue.add(jsonArrayRequest)
    }

    fun getAllCharacters(
        listener: (List<Character_data>) -> Unit,
        errorListener: (VolleyError) -> Unit
    ) {
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            urlCharacters,
            null,
            { response: JSONArray ->
                try {
                    val characters = mutableListOf<Character_data>()
                    for (i in 0 until response.length()) {
                        val character = response.getJSONObject(i)
                        val id = character.getInt("id")
                        val frenchName = character.getString("french_name")
                        val job = character.getString("job")
                        val size = character.getString("size")
                        val birthday = character.getString("birthday")
                        val age = character.getString("age")
                        val bounty = character.getString("bounty")
                        val status = character.getString("status")
                        val crewId = character.optInt("crewId")
                        val fruitId = character.optInt("fruitId")
                        val secondFruitId = character.optInt("2ndFruitId")

                        val characterData = Character_data(
                            id,
                            frenchName,
                            job,
                            size,
                            birthday,
                            age,
                            bounty,
                            status,
                            crewId,
                            fruitId,
                            secondFruitId
                        )
                        characters.add(characterData)
                    }
                    listener(characters)
                } catch (e: JSONException) {
                    errorListener(VolleyError(e.message))
                }
            },
            { error: VolleyError? ->
                error?.let { errorListener(it) }
            }
        )

        queue.add(jsonArrayRequest)
    }

    fun checkCharacterExists(
        characterName: String,
        successListener: (Boolean) -> Unit,
        errorListener: (VolleyError) -> Unit
    ) {
        val url = "$urlCharacters?name=$characterName"

        val request = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                val characterExists = response.length() > 0
                successListener(characterExists)
            },
            { error ->
                errorListener(error)
            }
        )

        queue.add(request)
    }

    fun getAllCrews(
        listener: (List<Crew_data>) -> Unit,
        errorListener: (VolleyError) -> Unit
    ) {
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            urlCrews,
            null,
            { response: JSONArray ->
                try {
                    val crews = mutableListOf<Crew_data>()
                    for (i in 0 until response.length()) {
                        val tripulation = response.getJSONObject(i)
                        val id = tripulation.getInt("id")
                        val frenchName = tripulation.getString("french_name")
                        val romanName = tripulation.getString("roman_name")
                        val description = tripulation.getString("description")
                        val totalPrime = tripulation.getString("total_prime")
                        val number = tripulation.getString("number")
                        val status = tripulation.getString("status")
                        val affiliation = tripulation.getString("affiliation")

                        val tripulationData = Crew_data(
                            id,
                            frenchName,
                            romanName,
                            description,
                            totalPrime,
                            number,
                            status,
                            affiliation
                        )
                        crews.add(tripulationData)
                    }
                    listener(crews)
                } catch (e: JSONException) {
                    errorListener(VolleyError(e.message))
                }
            },
            { error: VolleyError? ->
                error?.let { errorListener(it) }
            }
        )

        queue.add(jsonArrayRequest)
    }

    fun getAllMembers(
        crewId: String,
        listener: Response.Listener<List<Character_data>>,
        errorListener: Response.ErrorListener
    ) {
        val url = "https://api.api-onepiece.com/characters/crew/$crewId"

        val characterRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener { response ->
                val characterDataList = parseCharacterDataList(response)
                listener.onResponse(characterDataList)
            },
            errorListener
        )

        // Agregar la solicitud a la cola de Volley
        queue.add(characterRequest)
    }


    private fun parseCharacterDataList(response: JSONArray): List<Character_data> {
        val characterDataList = mutableListOf<Character_data>()

        for (i in 0 until response.length()) {
            try {
                val characterObject = response.getJSONObject(i)
                val id = characterObject.getInt("id")
                val frenchName = characterObject.getString("french_name")
                val job = characterObject.getString("job")
                val size = characterObject.getString("size")
                val birthday = characterObject.getString("birthday")
                val age = characterObject.getString("age")
                val bounty = characterObject.getString("bounty")
                val status = characterObject.getString("status")
                val crewId = characterObject.getInt("crew_id")
                val fruitId = characterObject.optInt("fruit_id")
                val secondFruitId = characterObject.optInt("2nd_fruit_id")

                val characterData = Character_data(
                    id,
                    frenchName,
                    job,
                    size,
                    birthday,
                    age,
                    bounty,
                    status,
                    crewId,
                    fruitId.takeIf { it != -1 },
                    secondFruitId.takeIf { it != -1 }
                )

                characterDataList.add(characterData)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

        return characterDataList
    }
}
open class SingletonHolder<out T, in A>(private val constructor: (A) -> T) {
    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A): T =
        instance ?: synchronized(this) {
            instance ?: constructor(arg).also { instance = it }
        }
}

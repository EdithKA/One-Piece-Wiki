package com.example.onepiecewiki.Characters

import com.example.onepiecewiki.Favorites.FavoriteCharacter_data
import com.example.onepiecewiki.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ModifyCharacterPresenter(private val view: ModifyCharacterActivity) {

    private val model = Model(view)

    private var character: Character_data? = null
    var isFavorite: String = "No" // Inicialmente "No"

    fun onViewCreated(character: Character_data?) {
        this.character = character

        character?.let {
            view.showCharacterDetails(
                it.frenchName,
                it.bounty,
                it.birthday,
                it.status,
                it.age,
                it.job,
                it.size
            )
            checkIfCharacterIsFavorite(it.character_favorite) //Comprobamos si el personaje que estamos modificando está en la lista de favoritos
        }
    }

    fun save(
        name: String,
        job: String,
        size: String,
        birthday: String,
        age: String,
        bounty: String,
        status: String
    ) {
        //Actualizamos el personaje con los nuevos datos modificados
        val updatedCharacter = Character_data(
            id = character?.id ?: 0,
            frenchName = name,
            job = job,
            size = size,
            birthday = birthday,
            age = age,
            bounty = bounty,
            status = status,
            crewId = character?.crewId ?: 0,
            fruitId = character?.fruitId ?: 0,
            secondFruitId = character?.secondFruitId ?: 0,
            character_favorite = isFavorite // Pasamos el valor actual de isArcFavorite
        )


        model.updateCharacter(updatedCharacter) { //Actualizamos el personaje con los nuevos datos
            view.showMessage("Personaje actualizado correctamente.")
            view.returnMainActivity() //Volvemos a la pantalla principal
        }
    }

    fun addFavorite() {
        isFavorite = if (isFavorite == "No") "Sí" else "No" // Cambiamos el valor de "Sí" a "No" o viceversa
    }

    fun deleteCharacter() { //Borramos el personaje de nuestra base de datos
        character?.let { character ->
            model.deleteCharacter(character) {
                view.showMessage("Personaje eliminado correctamente.")
                view.returnMainActivity()
            }
        }
    }

    private fun checkIfCharacterIsFavorite(character_favorite: String) {
        isFavorite = character_favorite
        view.setFavoriteState(isFavorite)
    }




}

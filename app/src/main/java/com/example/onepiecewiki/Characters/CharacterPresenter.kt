package com.example.onepiecewiki.Characters

import com.example.onepiecewiki.Model

class CharacterPresenter(private val view: CharactersActivity, private val name: String? = null) {

    private val model: Model = Model(view)

    init {
        if (name == null) { //Si la barra de búsqueda está vacia (al iniciar la vista siempre lo estará) mostramos todos los personajes
            getAllCharacters()
        } else {
            searchCharacter(name) //Si contiene algo de texto buscará los personajes que coincidan con el texto
        }
    }

    fun getAllCharacters() { //Función para mostrar todos los personajes de la base de datos
        model.getAllCharacters(
            listener = { characters ->
                view.showCharacters(characters)
            },
            errorListener = { error ->
                view.showMessage(error.message ?: "Error obteniendo los personajes")
            }
        )
    }

    fun searchCharacter(name: String) { //Función para buscar un personaje en la lista por su nombre
        model.searchCharacterName(
            name  = name,
            listener = { characters ->
                view.showCharacters(characters)
            }
        )
    }

    fun onCharacterClicked(character: Character_data) { //Al pulsar un personaje de la lista se muestra un dialog con información de este
        showCharacterDetailsDialog(character)
    }

    private fun showCharacterDetailsDialog(character: Character_data) {
        //Se crea una ventana del tipo dialog para mostrar información del personaje pulsado
        val dialog = CharacterDetailsDialog.newInstance(view, character)
        dialog.show(view.supportFragmentManager, "CharacterDetailsDialog")
    }
}
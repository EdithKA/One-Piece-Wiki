package com.example.onepiecewiki.Favorites

import com.example.onepiecewiki.Model

class FavoriteCharactersPresenter(private val view: FvCharactersActivity) {

    private val model: Model = Model(view)


    //Mostramos la lista completa de personajes favoritos
    fun getFavoriteCharacters() {
        model.getAllFavoriteCharacters(
            listener = { characters ->
                view.showCharacters(characters)
            },
            errorListener = { error -> //En el caso de que ocurriese un error
                view.showMessage(error.message ?: "Error al mostrar los personajes favoritos")
            }
        )
    }


    //No debe ocurrir nada al pulsar sobre un personaje de la lista de personajes
    //favoritos asi que dejo la función vacia para que no falle la aplicación al pulsar uno
    fun onCharacterClicked(character: FavoriteCharacter_data) {

    }


}

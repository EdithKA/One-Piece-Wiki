package com.example.onepiecewiki.Favorites

import android.content.Intent
import com.example.onepiecewiki.MainActivity
import com.example.onepiecewiki.Model

class FavoritesPresenter(private val view: FavoritesActivity) {

    private val model: Model = Model(view)

    fun toMain() { //Función para volver a la pantalla principal
        val intent = Intent(view, MainActivity::class.java)
        view.startActivity(intent)

    }

    fun toFavoriteCharacters() { //Función para ir a la pantalla de personajes favoritos
        val intent = Intent(view, FvCharactersActivity::class.java)
        view.startActivity(intent)

    }

    fun toFavoriteArcs() { //Función para ir a la pantalla de arcos favoritos
        val intent = Intent(view, FvArcActivity::class.java)
        view.startActivity(intent)

    }

    fun checkFavoritesArcs(){
        val favoriteArcs = model.database.dao.getAllFavoriteArcs();


        if(favoriteArcs.isNotEmpty()) {
            toFavoriteArcs()
        }
        else{
            view.showMessage("No existen arcos favoritos")
        }
    }

    fun checkFavoritesCharacters(){
        val favoriteCharacters = model.database.dao.getAllFavoriteCharacters()

        if(favoriteCharacters.isNotEmpty()) {
            toFavoriteCharacters()
        }
        else{
            view.showMessage("No existen personajes favoritos")
        }
    }
}

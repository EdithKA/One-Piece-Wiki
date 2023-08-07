package com.example.onepiecewiki.Favorites

import android.content.Intent
import com.example.onepiecewiki.MainActivity

class FavoritesPresenter(private val view: FavoritesActivity) {

    fun toMain() { //Función para volver a la pantalla principal
        val intent = Intent(view, MainActivity::class.java)
        view.startActivity(intent)
        view.finish()
    }

    fun toFavoriteCharacters() { //Función para ir a la pantalla de personajes favoritos
        val intent = Intent(view, FvCharactersActivity::class.java)
        view.startActivity(intent)
        view.finish()
    }

    fun toFavoriteArcs() { //Función para ir a la pantalla de arcos favoritos
        val intent = Intent(view, FvArcActivity::class.java)
        view.startActivity(intent)
        view.finish()
    }
}

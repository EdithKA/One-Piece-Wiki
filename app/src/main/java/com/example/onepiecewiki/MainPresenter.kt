package com.example.onepiecewiki

import android.content.Intent
import android.content.res.Configuration
import com.example.onepiecewiki.Arcs.ArcsActivity
import com.example.onepiecewiki.Characters.CharactersActivity
import com.example.onepiecewiki.Favorites.FavoritesActivity
import com.example.onepiecewiki.Crews.CrewsActivity

class MainPresenter(private val view: MainActivity) {


    private val model: Model = Model(view)

    fun onExitButtonClicked() { //Botón de salir
        view.finish()
    }

    fun onArcButtonClicked() { //Botón de la lista de arcos
        val intent = Intent(view, ArcsActivity::class.java)
        view.startActivity(intent)

    }

    fun onCharactersButtonClicked() { //Botón de la lista de personajes
        val intent = Intent(view, CharactersActivity::class.java)
        view.startActivity(intent)

    }

    fun onFavoritesButtonClicked() { //Botón de la lista de favoritos
        val intent = Intent(view, FavoritesActivity::class.java)
        view.startActivity(intent)

    }

    fun onTripulationButtonClicked() { //Botón de la lista de tripulaciones
        val intent = Intent(view, CrewsActivity::class.java)
        view.startActivity(intent)

    }

    //Observamos si existen favoritos
    fun checkFavorites(){

        val favoriteArcs = model.database.dao.getAllFavoriteArcs();
        val favoriteCharacters = model.database.dao.getAllFavoriteCharacters();

        if(favoriteArcs.isNotEmpty() || favoriteCharacters.isNotEmpty()) {
            onFavoritesButtonClicked()
        }
        else{
            view.showMessage("No existen favoritos")
        }

    }
}

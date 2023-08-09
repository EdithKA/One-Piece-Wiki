package com.example.onepiecewiki.Favorites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.onepiecewiki.R

class FavoritesActivity : AppCompatActivity() {

    private lateinit var presenter: FavoritesPresenter

    private lateinit var backButton: Button
    private lateinit var characterButton: Button
    private lateinit var arcButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        presenter = FavoritesPresenter(this)

        //Elementos de la pantalla
        characterButton = findViewById(R.id.fv_characters)
        arcButton = findViewById(R.id.fv_arcs)


        //Funciones para el funcionamiento de los botones
        favoriteCharacters()
        favoriteArcs()
    }


    private fun favoriteCharacters(){ //Ir a la pantalla de personajes favoritos
        characterButton.setOnClickListener {
            presenter.toFavoriteCharacters()
        }
    }

    private fun favoriteArcs(){ //Ir a la pantalla de arcos favoritos
        arcButton.setOnClickListener {
            presenter.toFavoriteArcs()
        }
    }
    override fun startActivity(intent: Intent) {
        super.startActivity(intent)

    }
}

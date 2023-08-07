package com.example.onepiecewiki.Favorites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onepiecewiki.R

class FvCharactersActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavoriteCharacterAdapter
    private lateinit var presenter: FavoriteCharactersPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fv_characters)

        //Elementos de la pantalla de personajes favoritos
        recyclerView = findViewById(R.id.fv_characters)


        presenter = FavoriteCharactersPresenter(this)
        presenter.getFavoriteCharacters()

        adapter = FavoriteCharacterAdapter(presenter)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    fun showCharacters(characters: List<FavoriteCharacter_data>) { //Mostrar la lista de personajes favoritos
        adapter.setData(characters)
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}
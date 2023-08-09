package com.example.onepiecewiki.Characters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onepiecewiki.MainActivity
import com.example.onepiecewiki.R

class CharactersActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CharacterAdapter
    private lateinit var presenter: CharacterPresenter
    private lateinit var addButton: Button
    private lateinit var searchBar : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        //Elementos del layout
        recyclerView = findViewById(R.id.characters)
        addButton = findViewById(R.id.btañadir)
        searchBar=findViewById(R.id.search)


        presenter = CharacterPresenter(this)
        adapter = CharacterAdapter(presenter)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addCharacter()
        searchCharacter()


        //Mostramos todos los personajes o los que coincidan con lo estipulado en la barra de búsqueda
        if (savedInstanceState != null) {
            val name = savedInstanceState.getString("name")
            presenter = CharacterPresenter(this, name)
        } else {
            presenter = CharacterPresenter(this)
        }

    }

    fun showCharacters(characters: List<Character_data>) { //Mostramos la lista de personajes en la pantalla
        adapter.setData(characters)
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun addCharacter() {  //Pasamos a la pestaña para añadir un nuevo personaje
        addButton.setOnClickListener {
            val intent = Intent(this, AddCharacterActivity::class.java)
            startActivity(intent)

        }
    }



    private fun searchCharacter(){  //Configuramos la barra de busqueda para poder buscar un personaje en nuestra lista
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.searchCharacter(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

    }


}

package com.example.onepiecewiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private lateinit var presenter: MainPresenter


    private lateinit var arcBt : Button
    private lateinit var charBt : Button
    private lateinit var favBt : Button
    private lateinit var tripulationBt : Button
    private lateinit var netWork : Network
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)

        supportActionBar?.title = "One Piece Wiki"

        netWork = Network.getInstance(this)



        passToArcs()
        passToChar()
        passToFav()
        passToTripulation()
    }



    private fun passToArcs() { //Botón para acceder a la lista de arcos
        arcBt = findViewById(R.id.botonArcos)
        arcBt.setOnClickListener {
            presenter.onArcButtonClicked()
        }
    }

    private fun passToChar() { //Botón para acceder a la lista de personajes
        charBt = findViewById(R.id.botonPersonajes)
        charBt.setOnClickListener {
            presenter.onCharactersButtonClicked()
        }
    }

    private fun passToFav() { //Botón para acceder a la lista de favoritos
        favBt = findViewById(R.id.botonFav)
        favBt.setOnClickListener {

            presenter.checkFavorites()
        }
    }



    private fun passToTripulation(){ //Botón para acceder a la lista de tripulaciones
        tripulationBt = findViewById(R.id.tripulation_bt)
        tripulationBt.setOnClickListener {
            presenter.onTripulationButtonClicked()
        }
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }




}

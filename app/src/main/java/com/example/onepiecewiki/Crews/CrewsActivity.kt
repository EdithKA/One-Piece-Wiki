package com.example.onepiecewiki.Crews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onepiecewiki.MainActivity
import com.example.onepiecewiki.R

class CrewsActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CrewsAdapters
    private lateinit var presenter: CrewsPresenter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crews)

        //Elementos de la pantalla
        recyclerView = findViewById(R.id.crews)


        presenter = CrewsPresenter(this)
        presenter.getAllCrews()

        adapter = CrewsAdapters(presenter)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)



    }


    fun showCrews(crewDatalist: List<Crew_data>) { //Mostramos la lista de tripulaciones
        adapter.setData(crewDatalist)
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }




}

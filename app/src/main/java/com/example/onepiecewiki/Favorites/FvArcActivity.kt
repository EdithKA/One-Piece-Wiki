package com.example.onepiecewiki.Favorites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onepiecewiki.Arcs.Arc_data
import com.example.onepiecewiki.R

class FvArcActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavoriteArcsAdapter
    private lateinit var presenter: FavoriteArcsPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fv_arc)


        presenter = FavoriteArcsPresenter(this)
        presenter.getFavoriteArcs() //Obtener la lista de arcos favoritos

        //Elementos de la escena
        recyclerView = findViewById(R.id.fv_characters)
        adapter = FavoriteArcsAdapter(presenter)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


    }

    fun showArcs(arcs: List<Arc_data>) {
        adapter.setData(arcs)
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}
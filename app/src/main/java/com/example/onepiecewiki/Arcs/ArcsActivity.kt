package com.example.onepiecewiki.Arcs


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onepiecewiki.MainActivity
import com.example.onepiecewiki.R

class ArcsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArcAdapter
    private lateinit var presenter: ArcPresenter
    private lateinit var add_bt : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arcs)

        recyclerView = findViewById(R.id.losArcos)
        presenter = ArcPresenter(this)
        presenter.getAllArcs()
        adapter = ArcAdapter(presenter)


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        add_bt = findViewById(R.id.botonAñadir)

        addArc()
    }

    fun showArcs(arcs: List<Arc_data>) { //Mostramos la lista de arcos
        adapter.setData(arcs)
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun addArc() {
        add_bt.setOnClickListener {

            val intent = Intent(this, AddArcActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
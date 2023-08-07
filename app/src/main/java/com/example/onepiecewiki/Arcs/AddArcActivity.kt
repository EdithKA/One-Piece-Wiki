package com.example.onepiecewiki.Arcs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.onepiecewiki.MainActivity
import com.example.onepiecewiki.R

class AddArcActivity : AppCompatActivity() {

    private lateinit var tittleEditText: EditText
    private lateinit var episodesEditText: EditText
    private lateinit var chaptersEditText: EditText
    private lateinit var volumesEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var presenter: AddArcPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_arc)

        presenter = AddArcPresenter(this)

        tittleEditText = findViewById(R.id.tittle_txt)
        chaptersEditText = findViewById(R.id.chapters_txt)
        episodesEditText = findViewById(R.id.episodes_txt)
        volumesEditText = findViewById(R.id.volumes_txt)
        saveButton = findViewById(R.id.save_bt2)

        saveNewArc()

    }

    private fun saveNewArc(){ //Guardamos el nuevo arco con los datos añadidos
        saveButton.setOnClickListener {
            val title = tittleEditText.text.toString()
            val episodes = episodesEditText.text.toString()
            val chapters = chaptersEditText.text.toString()
            val volumes = volumesEditText.text.toString()

            presenter.save(title, episodes, chapters, volumes)
        }
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun returnMainActivity() {  //Al guardar el arco volvemos a la pantalla principal
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}

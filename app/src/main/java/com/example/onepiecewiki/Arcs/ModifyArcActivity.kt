package com.example.onepiecewiki.Arcs

import ModifyArcPresenter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.onepiecewiki.MainActivity
import com.example.onepiecewiki.R

class ModifyArcActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var episodesEditText: EditText
    private lateinit var chaptersEditText: EditText
    private lateinit var volumesEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var favorite_check: CheckBox
    private lateinit var deleteButton: Button

    private lateinit var presenter: ModifyArcPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_arc)

        val arc: Arc_data? = intent.getParcelableExtra("arc")


        titleEditText = findViewById(R.id.tittle_txt)
        chaptersEditText = findViewById(R.id.chapters_txt)
        episodesEditText = findViewById(R.id.episodes_txt)
        volumesEditText = findViewById(R.id.volumes_txt)
        saveButton = findViewById(R.id.save_bt2)
        favorite_check = findViewById(R.id.favorite_check)
        deleteButton = findViewById(R.id.delete_button)

        presenter = ModifyArcPresenter(this)

        presenter.onViewCreated(arc)

        updateArc()
        deleteArc()
        addFavorite()


    }


    private fun deleteArc(){ //Borramos el arco de la base de datos
        deleteButton.setOnClickListener {
            presenter.deleteArc()
        }
    }
    private fun addFavorite() {
        favorite_check.setOnCheckedChangeListener { _, isChecked ->
            presenter.addFavorite()
        }
    }


    private fun updateArc(){ //Actualizamos el arco
        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val episodes = episodesEditText.text.toString()
            val chapters = chaptersEditText.text.toString()
            val volumes = volumesEditText.text.toString()
            val favorite = presenter.isFavorite
            presenter.save(title, episodes, chapters, volumes, favorite)
        }
    }



    fun showArcDetails(title: String, episodes: String, chapters: String, volumes: String, favorite : String) { //Mostramos en los campos especificados
        titleEditText.setText(title)                                                           //la información actual del arco
        episodesEditText.setText(episodes)                                                     //que queremos modificar
        chaptersEditText.setText(chapters)
        volumesEditText.setText(volumes)


    }

    fun setFavoriteState(isFavorite: String) {
        favorite_check.isChecked = isFavorite == "Sí"
    }

    fun showMessage(message: String) {
        runOnUiThread {
            Toast.makeText(this@ModifyArcActivity, message, Toast.LENGTH_SHORT).show()
        }
    }

    fun returnMainActivity() { //Volvemos a la pantalla de inicio
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}

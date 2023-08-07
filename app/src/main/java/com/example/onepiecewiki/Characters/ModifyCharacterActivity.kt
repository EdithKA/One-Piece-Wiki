package com.example.onepiecewiki.Characters

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.onepiecewiki.MainActivity
import com.example.onepiecewiki.R
import com.example.onepiecewiki.WikiDatabase

class ModifyCharacterActivity : AppCompatActivity() {

    private lateinit var presenter: ModifyCharacterPresenter

    private lateinit var nameEditText: EditText
    private lateinit var bountyEditText: EditText
    private lateinit var jobEditText: EditText
    private lateinit var statusEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var sizeEditText: EditText
    private lateinit var birthdayEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var database: WikiDatabase
    private lateinit var deleteButton: Button
    private lateinit var favCheckBox: CheckBox

    private var character: Character_data? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_character)

        database = WikiDatabase.getInstance(this)


        character = intent.getParcelableExtra("character")

        nameEditText = findViewById(R.id.name_txt2)
        bountyEditText = findViewById(R.id.bounty_txt2)
        jobEditText = findViewById(R.id.job_txt2)
        statusEditText = findViewById(R.id.status_txt2)
        ageEditText = findViewById(R.id.age_txt2)
        sizeEditText = findViewById(R.id.size_txt2)
        birthdayEditText = findViewById(R.id.birthday_txt2)
        saveButton = findViewById(R.id.save_button2)
        deleteButton = findViewById(R.id.delete_bt)
        favCheckBox = findViewById(R.id.favorite_checkbox_character)

        presenter = ModifyCharacterPresenter(this)
        presenter.onViewCreated(character)

        saveCharacter()
        deleteCharacter()
        addFavorite()


    }

    private fun deleteCharacter(){ //Función para el comportamiento del botón de borrar personaje
        deleteButton.setOnClickListener {
            presenter.deleteCharacter()
        }
    }
    private fun saveCharacter(){ //Función para el comportamiento del botón de guardar
        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val status = statusEditText.text.toString()
            val bounty = bountyEditText.text.toString()
            val age = ageEditText.text.toString()
            val job = jobEditText.text.toString()
            val size = sizeEditText.text.toString()
            val birthday = birthdayEditText.text.toString()
            val favorite = presenter.isFavorite

            presenter.save(name, job, size, birthday, age, bounty, status)
        }
    }

    private fun addFavorite() {
        favCheckBox.setOnCheckedChangeListener { _, isChecked ->
            presenter.addFavorite()
        }
    }

    fun setFavoriteState(isFavorite: String) {
        favCheckBox.isChecked = isFavorite == "Sí"
    }


    fun showCharacterDetails(  //Mostrar los datos actuales del personaje en los campos que le corresponden en la pantalla de modificar
        name: String,
        bounty: String,
        birthday: String,
        status: String,
        age: String,
        job: String,
        size: String
    ) {
        nameEditText.setText(name)
        bountyEditText.setText(bounty)
        birthdayEditText.setText(birthday)
        statusEditText.setText(status)
        ageEditText.setText(age)
        jobEditText.setText(job)
        sizeEditText.setText(size)
    }

    fun showMessage(message: String) {
        runOnUiThread {
            Toast.makeText(this@ModifyCharacterActivity, message, Toast.LENGTH_SHORT).show()
        }
    }

    fun returnMainActivity() { //Volver a la pantalla principal
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
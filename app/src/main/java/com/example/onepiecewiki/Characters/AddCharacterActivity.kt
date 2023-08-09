package com.example.onepiecewiki.Characters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.onepiecewiki.Characters.AddCharacterPresenter
import com.example.onepiecewiki.Characters.CharactersActivity
import com.example.onepiecewiki.MainActivity
import com.example.onepiecewiki.R
import com.example.onepiecewiki.WikiDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCharacterActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var jobEditText: EditText
    private lateinit var sizeEditText: EditText
    private lateinit var birthdayEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var bountyEditText: EditText
    private lateinit var statusEditText: EditText
    private lateinit var saveButton: Button


    private lateinit var presenter: AddCharacterPresenter
    private lateinit var database: WikiDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_character)

        presenter = AddCharacterPresenter(this)
        database = WikiDatabase.getInstance(this)

        //elementos de la vista
        nameEditText = findViewById(R.id.french_name_edittext2)
        jobEditText = findViewById(R.id.job_txt2)
        sizeEditText = findViewById(R.id.size_txt2)
        birthdayEditText = findViewById(R.id.birthday_txt2)
        ageEditText = findViewById(R.id.age_txt2)
        bountyEditText = findViewById(R.id.bounty_txt2)
        statusEditText = findViewById(R.id.status_txt2)
        saveButton = findViewById(R.id.save_button2)




    }

    private fun saveCharacter(){ //Para guardar el nuevo personaje en la lista de personajes de nuestra base de datos
        saveButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val job = jobEditText.text.toString().trim()
            val size = sizeEditText.text.toString().trim()
            val birthday = birthdayEditText.text.toString().trim()
            val age = ageEditText.text.toString().trim()
            val bounty = bountyEditText.text.toString().trim()
            val status = statusEditText.text.toString().trim()

            if (name.isNotEmpty()) {
                presenter.save(name, job, size, birthday, age, bounty, status)
            } else {
                //No se podrá guardar el nuevo personaje si no le ponemos un nombre
                showToast("Introduce un nombre para tu personaje")
            }
        }
    }

    fun showMessage(message: String) {
        showToast(message)
    }

    fun returnMainActivity() { //Para volver a la pantalla principal
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

    private fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}

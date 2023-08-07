package com.example.onepiecewiki.Characters

import com.example.onepiecewiki.Arcs.AddArcActivity
import com.example.onepiecewiki.Arcs.Arc_data
import com.example.onepiecewiki.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddCharacterPresenter(private val view: AddCharacterActivity) {

    private val model = Model(view)
    private var exist : Boolean = false;

    fun onViewCreated(character: Character_data) {

    }

    fun save(
        name: String,
        job: String,
        size: String,
        birthday: String,
        age: String,
        bounty: String,
        status: String
    ) {
        //Creamos un nuevo objeto personaje con los datos introducidos en cada uno de los campos
        val newCharacter = Character_data(
            id = 0,
            frenchName = name,
            job = job,
            size = size,
            birthday = birthday,
            age = age,
            bounty = bounty,
            status = status,
            crewId = null,
            fruitId = null,
            secondFruitId = null
        )

        //Comprobamos si existe un personaje con ese nombre en la base de datos y en el caso de que no sea así añadiremos
        //el nuevo objeto personaje a ella
        GlobalScope.launch(Dispatchers.Main) {
            checkIfCharacterExist(newCharacter.frenchName)
            if (exist) {
                view.showMessage("Ya existe un personaje con ese nombre")
            } else {
                model.insertCharacter(newCharacter) {
                    view.showMessage("Personaje guardado correctamente.")
                    view.returnMainActivity() //Volvemos a la pantalla principal tras guardar el nuevo personaje
                }
            }
        }
    }

    private fun checkIfCharacterExist(name: String) { //Función para comprobar si existe un personaje con ese nombre en la base de datos
        GlobalScope.launch(Dispatchers.Main) {
            val exist = model.checkCharacterExist(name)
            this@AddCharacterPresenter.exist = exist
        }
    }
}

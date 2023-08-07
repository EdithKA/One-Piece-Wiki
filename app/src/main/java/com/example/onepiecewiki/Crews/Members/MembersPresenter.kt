package com.example.onepiecewiki.Crews.Members

import com.example.onepiecewiki.Characters.CharacterDetailsDialog
import com.example.onepiecewiki.Characters.Character_data
import com.example.onepiecewiki.Model

class MembersPresenter(private val view: MembersActivity) {


    private val model: Model = Model(view)

    private var currentCrewId: Int? = null
    private lateinit var currentCrewName: String


    fun getAllMembers() {
        currentCrewId?.let { crew_id ->
            model.getAllMembers(
                crew_id,
                listener = { characters ->
                    view.showMembers(characters)
                },
                errorListener = { error ->
                    view.showMessage(error.message ?: "No hay miembros disponibles")
                }
            )
        }
    }

    fun getCrewName() { //Obtenemos el nombre de la tripulación seleccionada para escribirlo en el textview
        currentCrewId?.let { crewId ->
            model.getCrewById(
                crewId,
                listener = { crew ->
                    currentCrewName = crew.french_name
                    view.setCrewName(currentCrewName)
                }
            )
        }
    }

    fun onMemberClicked(character: Character_data) {
        showCharacterDetailsDialog(character)
    }

    private fun showCharacterDetailsDialog(character: Character_data) {
        val dialog = CharacterDetailsDialog.newInstance(view, character)
        dialog.show(view.supportFragmentManager, "CharacterDetailsDialog")
    }


    fun setCurrentCrew(crewId: Int) {
        currentCrewId = crewId
    }




}
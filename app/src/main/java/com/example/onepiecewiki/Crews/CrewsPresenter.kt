package com.example.onepiecewiki.Crews

import com.example.onepiecewiki.Model

class CrewsPresenter(private val view: CrewsActivity) {
    private val model = Model(view.applicationContext)

    fun getAllCrews() {
        model.getAllCrews(
            listener = { crewDatalist ->
                view.showCrews(crewDatalist)
            },
            errorListener = { error ->
                view.showMessage("Error: ${error.message}")
            }
        )
    }

    fun onCrewClicked(crew: Crew_data) {
        showCrewDetailDialog(crew)
    }

    private fun showCrewDetailDialog(crew: Crew_data) {
        val dialog = CrewDetailsDialog.newInstance(view, crew)
        dialog.show(view.supportFragmentManager, "CrewDetailsDialog")
    }
}

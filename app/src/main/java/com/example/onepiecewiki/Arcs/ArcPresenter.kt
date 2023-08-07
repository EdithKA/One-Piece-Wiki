package com.example.onepiecewiki.Arcs

import com.example.onepiecewiki.Arcs.ArcDetailsDialog
import com.example.onepiecewiki.Arcs.Arc_data
import com.example.onepiecewiki.Arcs.ArcsActivity
import com.example.onepiecewiki.Model


class ArcPresenter(private val view: ArcsActivity) {

    private val model: Model = Model(view)
    
    fun getAllArcs() {  //Lllamamos al model para obtener la lista de arcos completa
        model.getAllArcs(
            listener = { arcs ->
                view.showArcs(arcs)
            },
            errorListener = { error -> //En el caso de que ocurra un error al obtener la lista se mostrará un aviso
                view.showMessage(error.message ?: "Error al cargar los arcos")
            }
        )
    }

    fun onArcClicked(arc: Arc_data) { //Al pulsar un arco de la lista se mostrará un dialog con los detalles sobre este
        showArcDetailsDialog(arc)
    }

    private fun showArcDetailsDialog(arc: Arc_data) { //Los detalles se mostarán en un dialog "ArcsDetailsDialog"
        val dialog = ArcDetailsDialog.newInstance(view, arc)
        dialog.show(view.supportFragmentManager, "ArcDetailsDialog")
    }
}

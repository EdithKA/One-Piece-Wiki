package com.example.onepiecewiki.Favorites

import com.example.onepiecewiki.Arcs.Arc_data
import com.example.onepiecewiki.Model

class FavoriteArcsPresenter(private val view: FvArcActivity) {

    private val model: Model = Model(view)

    fun getFavoriteArcs() { //Obtenemos la lista completa de arcos favoritos para mostrarla en la pantalla
        model.getAllFavoriteArcs(
            listener = { arcs ->
                view.showArcs(arcs)
            },
            errorListener = { error -> //En el caso de que no se pudiesen obtener
                view.showMessage(error.message ?: "Error al mostrar los arcos favoritos")
            }
        )
    }


    //No tiene que ocurrir nada cuando se selecciona un arco de la lista de favoritos así
    //que dejo ésta función vacía para que la aplicacion no falle
    fun onFavoriteArcClicked(character: Arc_data) {

    }
}
package com.example.onepiecewiki.Arcs

import com.example.onepiecewiki.Arcs.AddArcActivity
import com.example.onepiecewiki.Arcs.Arc_data
import com.example.onepiecewiki.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddArcPresenter(private val view: AddArcActivity) {

    private val model = Model(view)

    private var arc: Arc_data? = null

    private var exist : Boolean = false;

    fun onViewCreated(arc: Arc_data?) {

    }

    fun save(title: String, episodes: String, chapters: String, volumes: String) { //Recolectamos los datos especificados en la pantalla
                                                                                    //y guardamos un nuevo arco con esas especificaciones
        val newArc = Arc_data(
            id = arc?.id ?: 0,
            arc_number = arc?.arc_number ?: "",
            arc_title = title,
            arc_chapters = chapters,
            arc_volume = volumes,
            arc_episode = episodes
        )

        //Detectamos si en la base de datos ya existe un arco con ese nombre
        GlobalScope.launch(Dispatchers.Main) {
            checkIfArcExist(newArc.arc_title)
            if (exist) {
                view.showMessage("Ya existe un arco con ese nombre") //En el caso de que existe no podremos guardarlo y deberemos cambiarlo
            } else {
                model.insertArc(newArc) {
                    view.showMessage("Arco actualizado correctamente.") //Si no existe se guardará el nuevo arco en la base de datos
                    view.returnMainActivity()                           //y volveremos a la pantalla principal
                }
            }
        }
    }


    private fun checkIfArcExist(arcTitle: String) { //Comprobamos si existe un arco con ese nombre en nuestra base de datos
        GlobalScope.launch(Dispatchers.Main) {
            val exist = model.checkArcExist(arcTitle)
            this@AddArcPresenter.exist = exist //Modificamos el booleano dependiendo de si existe o no
        }
    }


}
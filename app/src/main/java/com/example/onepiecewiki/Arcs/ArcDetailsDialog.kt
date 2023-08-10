package com.example.onepiecewiki.Arcs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.onepiecewiki.Characters.ModifyCharacterActivity

class ArcDetailsDialog: DialogFragment() {


    private lateinit var activity: AppCompatActivity

    companion object {
        fun newInstance(activity: AppCompatActivity,   arc: Arc_data): ArcDetailsDialog {
            val args = Bundle().apply {
                putParcelable("arc", arc)
            }
            val fragment = ArcDetailsDialog()
            fragment.arguments = args
            fragment.activity = activity
            return fragment
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val arc = arguments?.getParcelable<Arc_data>("arc")

        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setTitle(arc?.arc_title)
            .setMessage(getArcDetails(arc)) //Detalles del arco seleccionado
            .setPositiveButton("Cerrar") { dialog, _ -> //Botón para cerrar el dialog
                dialog.dismiss()
            }
            .setNegativeButton("Modificar") { dialog, _ -> //Botón para entrar a la ventana de modificar el arco
                val intent = Intent(requireContext(), ModifyArcActivity::class.java)
                intent.putExtra("arc", arc)
                startActivity(intent)
                dialog.dismiss()
            }

        return dialogBuilder.create()
    }

    private fun getArcDetails(arc: Arc_data?): String { //Obtenemos los detalles del arco para rellenar el dialog
        val sb = StringBuilder()
        sb.append("Número: ${arc?.arc_number}\n")
        sb.append("Capítulos: ${arc?.arc_chapters}\n")
        sb.append("Episodios: ${arc?.arc_episode}\n")
        sb.append("Volúmenes: ${arc?.arc_volume}\n")


        return sb.toString()
    }
}
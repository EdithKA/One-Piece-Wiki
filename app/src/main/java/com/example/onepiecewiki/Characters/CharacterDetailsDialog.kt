package com.example.onepiecewiki.Characters

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

class CharacterDetailsDialog : DialogFragment() {

    private lateinit var activity: AppCompatActivity

    companion object {

        private const val MODIFY_CHARACTER_REQUEST_CODE = 1
        fun newInstance(activity: AppCompatActivity,   character: Character_data): CharacterDetailsDialog {
            val args = Bundle().apply {
                putParcelable("character", character)
            }
            val fragment = CharacterDetailsDialog()
            fragment.arguments = args
            fragment.activity = activity
            return fragment


        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog { //Creamos el dialog con la información del personaje elegido
        val character = arguments?.getParcelable<Character_data>("character")

        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setTitle(character?.frenchName)
            .setMessage(getCharacterDetailsMessage(character))
            .setPositiveButton("Cerrar") { dialog, _ -> //Cerrar la ventana emergente
                dialog.dismiss()
            }
            .setNeutralButton("Modificar") { dialog, _ -> //Modificar el personaje seleccionado
                val intent = Intent(requireContext(), ModifyCharacterActivity::class.java)
                intent.putExtra("character", character)
                startActivity(intent)
                dialog.dismiss()
            }

        return dialogBuilder.create()
    }

    private fun getCharacterDetailsMessage(character: Character_data?): String { //mostramos los datos del personaje elegido en el dialog
        val sb = StringBuilder()
        sb.append("Nombre: ${character?.frenchName}\n")
        sb.append("Edad: ${character?.age}\n")
        sb.append("Estado: ${character?.status}\n")


        val bounty = character?.bounty
        if (bounty != null && bounty != "") {
            sb.append("Recompensa: ${character.bounty}\n")
        }



        return sb.toString()
    }
}


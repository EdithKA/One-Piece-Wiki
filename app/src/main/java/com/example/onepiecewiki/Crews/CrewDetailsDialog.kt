package com.example.onepiecewiki.Crews

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.onepiecewiki.Crews.Members.MembersActivity


class CrewDetailsDialog : DialogFragment() {

    private lateinit var activity: AppCompatActivity

    companion object {
        private const val ARG_CREW = "crew"

        fun newInstance(activity: AppCompatActivity, crew: Crew_data): CrewDetailsDialog {
            val args = Bundle().apply {
                putParcelable(ARG_CREW, crew)
            }
            val fragment = CrewDetailsDialog()
            fragment.arguments = args
            fragment.activity = activity
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val crew = arguments?.getParcelable<Crew_data>(ARG_CREW)

        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setTitle(crew?.french_name)
            .setMessage(getCrewDetailMessage(crew))
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog.dismiss()
            }
            .setNeutralButton("Miembros") { dialog, _ ->

                val crewId = crew?.id
                val intent = Intent(requireContext(), MembersActivity::class.java)
                intent.putExtra("CREW_ID", crewId)
                startActivity(intent)
                activity.finish()
                dialog.dismiss()

            }

        return dialogBuilder.create()
    }

    private fun getCrewDetailMessage(crew: Crew_data?): String {
        val message = "Nombre: ${crew?.french_name}\n" +
                "Afiliación: ${crew?.affiliation}\n" +
                "Estatus: ${crew?.status}\n" +
                "Botin total: ${crew?.total_prime}\n"

        return message
    }
}

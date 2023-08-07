package com.example.onepiecewiki.Crews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onepiecewiki.R

class CrewsAdapters(private val presenter: CrewsPresenter) : RecyclerView.Adapter<CrewsAdapters.CrewsViewHolder>() {

    private val crews: MutableList<Crew_data> = mutableListOf()


    //Utilizamos un layout auxiliar para mostrar los elementos de la lista de tripulaciones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_list, parent, false)
        return CrewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CrewsViewHolder, position: Int) {
        val crew = crews[position]
        holder.bind(crew)

        holder.itemView.setOnClickListener { //En el caso de que pulsemos un elemento de la lista
            presenter.onCrewClicked(crew)
        }
    }

    override fun getItemCount(): Int { //Obtenemos el número de elementos de la lista
        return crews.size
    }

    fun setData(data: List<Crew_data>) {
        crews.clear()
        crews.addAll(data)
        notifyDataSetChanged()
    }


    //Mostramos el id y el nombre de las tripulaciones en la lista
    inner class CrewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val id: TextView = itemView.findViewById(R.id.id_list)
        private val name: TextView = itemView.findViewById(R.id.name_list)

        fun bind(crew: Crew_data) {
            id.text = crew.id.toString()
            name.text = crew.french_name
        }
    }
}

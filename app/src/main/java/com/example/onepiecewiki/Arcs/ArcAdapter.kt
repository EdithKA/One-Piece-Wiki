package com.example.onepiecewiki.Arcs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onepiecewiki.R

class ArcAdapter(private val presenter: ArcPresenter) : RecyclerView.Adapter<ArcAdapter.ArcViewHolder>() {

    private val arcs: MutableList<Arc_data> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArcViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_list, parent, false)
        return ArcViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArcViewHolder, position: Int) {
        val arc = arcs[position]
        holder.bind(arc)

        holder.itemView.setOnClickListener { //Si pulsamos un arco de la lista
            presenter.onArcClicked(arc)
        }
    }


    override fun getItemCount(): Int { //Obtenemos el número de arcos
        return arcs.size
    }


    //Añadimos la lista de arcos a nuestro recycler
    fun setData(data: List<Arc_data>) {
        arcs.clear()
        arcs.addAll(data)
        notifyDataSetChanged()
    }



    inner class ArcViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { // Adaptamos a nuestro recyler view los datos
        private val id: TextView = itemView.findViewById(R.id.id_list)              //que queremos mostrar de nuestro arco
        private val name: TextView = itemView.findViewById(R.id.name_list)          //en la lista

        fun bind(arc: Arc_data) {
            name.text = arc.arc_title
            id.text = arc.arc_number
        }
    }
}

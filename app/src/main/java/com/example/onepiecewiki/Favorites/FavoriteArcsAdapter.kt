package com.example.onepiecewiki.Favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onepiecewiki.Arcs.Arc_data
import com.example.onepiecewiki.R

class FavoriteArcsAdapter(private val presenter: FavoriteArcsPresenter) : RecyclerView.Adapter<FavoriteArcsAdapter.FavoriteArcViewHolder>() {

    private val arcs: MutableList<Arc_data> = mutableListOf()


    //Utiliza el layout auxiliar "row_list" para mostar los elementos de nuestra lista en el recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteArcViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_list, parent, false)
        return FavoriteArcViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteArcViewHolder, position: Int) {
        val arc = arcs[position]
        holder.bind(arc)

        //Al seleccionar un arco favorito
        holder.itemView.setOnClickListener {
            presenter.onFavoriteArcClicked(arc)
        }
    }

    override fun getItemCount(): Int { //Obtiene el número de elementos que hay en nuestra lista
        return arcs.size
    }

    //Muestra la información dentro de la lista de arcos favoritos en el recyclerview
    fun setData(data: List<Arc_data>) {
        arcs.clear()
        arcs.addAll(data)
        notifyDataSetChanged()
    }

    inner class FavoriteArcViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Muestra la información de los arcos que deseemos en la lista, en este caso se mostrará el id de arco y su nombre
        private val id: TextView = itemView.findViewById(R.id.id_list)
        private val name: TextView = itemView.findViewById(R.id.name_list)

        fun bind(arc: Arc_data) {
            id.text = arc.id.toString()
            name.text = arc.arc_title

        }
    }
}
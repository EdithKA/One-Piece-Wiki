package com.example.onepiecewiki.Favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onepiecewiki.R

class FavoriteCharacterAdapter(private val presenter: FavoriteCharactersPresenter) : RecyclerView.Adapter<FavoriteCharacterAdapter.FavoriteCharacterViewHolder>() {

    private val characters: MutableList<FavoriteCharacter_data> = mutableListOf()


    //Mostramos el contenido de la lista de personajes favoritos utilizando un layout auxiliar "row_list"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_list, parent, false)
        return FavoriteCharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteCharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)

        holder.itemView.setOnClickListener { //En el caso de que pulsemos un personaje de la lista
            presenter.onCharacterClicked(character)
        }
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    //Obtenemos los datos de la lista de personajes favoritos para mostrarlos
    fun setData(data: List<FavoriteCharacter_data>) {
        characters.clear()
        characters.addAll(data)
        notifyDataSetChanged()
    }


    //Mostramos los datos que queramos de los personajes en nuestra lista, en el caso de los personajes
    //favoritos mostraremos su id y su nombre
    inner class FavoriteCharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val id: TextView = itemView.findViewById(R.id.id_list)
        private val name: TextView = itemView.findViewById(R.id.name_list)

        fun bind(character: FavoriteCharacter_data) {
            id.text = character.id.toString()
            name.text = character.frenchName
        }
    }
}

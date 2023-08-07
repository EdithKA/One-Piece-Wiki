package com.example.onepiecewiki.Characters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onepiecewiki.R

class CharacterAdapter(private val presenter: CharacterPresenter) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {


    private val characters: MutableList<Character_data> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        //Adaptamos el contenido de la lista de personajes de nuestra base de datos al recyclerview de nuestra
        //vista utilizando un layout complementario que nos permitirá mostrar dos características de cada elemento
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_list, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)


        //Si pulsamos un elemento de la lista
        holder.itemView.setOnClickListener {
            presenter.onCharacterClicked(character)
        }
    }

    override fun getItemCount(): Int { //Obtenemos el número de elementos que contiene la lista
        return characters.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Character_data>) { //Añadimos la lista al recyclerview
        characters.clear()
        characters.addAll(data)
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val id: TextView = itemView.findViewById(R.id.id_list)
        private val name: TextView = itemView.findViewById(R.id.name_list)

        //Para los personajes mostraremos su id y su nombre en la lista
        fun bind(character: Character_data) {
            id.text = character.id.toString()
            name.text = character.frenchName

        }


    }



}
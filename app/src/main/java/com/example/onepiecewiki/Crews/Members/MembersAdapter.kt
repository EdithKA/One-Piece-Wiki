package com.example.onepiecewiki.Crews.Members

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onepiecewiki.Characters.Character_data
import com.example.onepiecewiki.R

class MembersAdapter(private val presenter: MembersPresenter) : RecyclerView.Adapter<MembersAdapter.MemberViewHolder>() {

    private val members: MutableList<Character_data> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_list, parent, false)
        return MemberViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val member = members[position]
        holder.bind(member)
        holder.itemView.setOnClickListener {
            presenter.onMemberClicked(member)
        }
    }

    override fun getItemCount(): Int = members.size

    fun setData(members: List<Character_data>) {
        this.members.clear()
        this.members.addAll(members)
        notifyDataSetChanged()
    }

    inner class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val roleTextView: TextView = itemView.findViewById(R.id.id_list)
        private val nameTextView: TextView = itemView.findViewById(R.id.name_list)

        fun bind(member: Character_data) {
            nameTextView.text = member.frenchName
            roleTextView.text = member.job
        }
    }
}

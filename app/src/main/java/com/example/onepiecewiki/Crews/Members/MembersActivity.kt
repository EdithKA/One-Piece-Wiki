package com.example.onepiecewiki.Crews.Members

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onepiecewiki.Characters.Character_data
import com.example.onepiecewiki.R
import com.example.onepiecewiki.Crews.CrewsActivity

class MembersActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MembersAdapter
    private lateinit var presenter: MembersPresenter

    private lateinit var crewNameEditText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_members)

        recyclerView = findViewById(R.id.members_list)

        crewNameEditText = findViewById(R.id.crew_name)


        presenter = MembersPresenter(this)


        adapter = MembersAdapter(presenter)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)



        setCrew()

    }


    private fun setCrew(){
        val crewId = intent.getIntExtra("CREW_ID", -100)

        if (crewId != -100) {
            presenter.setCurrentCrew(crewId)
            presenter.getAllMembers()
            presenter.setCurrentCrew(crewId)
            presenter.getCrewName()
        } else {
            showMessage("Crew ID not provided")
        }
    }


    fun showMembers(members: List<Character_data>) {
        adapter.setData(members)
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    fun setCrewName(crew_name: String){
        crewNameEditText.text = crew_name
    }




}

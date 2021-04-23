package com.example.homework4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var buttonAdd:Button
    private lateinit var recyclerViewDreams: RecyclerView

    private val dreamViewModel:DreamViewModel by viewModels{
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonAdd=findViewById(R.id.button_add)
        recyclerViewDreams=findViewById(R.id.recyclerView_dreams)

        val adapter=DreamListAdapter(this)
        recyclerViewDreams.adapter=adapter
        recyclerViewDreams.layoutManager=LinearLayoutManager(this)

        dreamViewModel.allDreams.observe(this, Observer{
            dreams -> dreams?.let{
            adapter.submitList(it)
        }
        })

        buttonAdd.setOnClickListener {
            var intent = Intent(this@MainActivity, AddActivity::class.java)
            //intent.putExtra("update", 4)
            startActivity(intent)
        }


    }


}
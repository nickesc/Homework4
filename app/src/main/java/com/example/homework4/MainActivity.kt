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
    //private lateinit var adapter: DreamListAdapter
    //private lateinit var buttonSelect:Button
    //private lateinit var buttonDelete:Button
    //private lateinit var buttonUpdate:Button
    //private lateinit var databaseHelper: DatabaseHelper
    //private lateinit var dreams:MutableList<Dream>
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

        /*
        databaseHelper= DatabaseHelper(this)


        adapter=DreamAdapter(databaseHelper.getAll())
        recyclerViewDreams.adapter = adapter
        recyclerViewDreams.layoutManager= LinearLayoutManager(this)
        recyclerViewDreams.setHasFixedSize(true)
        //buttonSelect=findViewById(R.id.button_select)
        //buttonDelete=findViewById(R.id.button_delete)
        //buttonUpdate=findViewById(R.id.button_update)

        buttonAdd.setOnClickListener {
            val dream = Dream(0,"title","content","reflection","emotion")
            val rowID = databaseHelper.addDream(dream)
            Toast.makeText(this, ""+rowID, Toast.LENGTH_SHORT).show()
        }




        buttonSelect.setOnClickListener {

            dreams = databaseHelper.getAll()

            var selected=""

            for (dream in dreams){
                selected += dream.toString()
            }

            Log.d("help", selected
            )
        }


        buttonDelete.setOnClickListener {
            val rowID=databaseHelper.deleteDream(databaseHelper.getNewest().id)
            Log.d("help",""+rowID)
        }
        buttonUpdate.setOnClickListener {
            val rowID=databaseHelper.updateDream(databaseHelper.getNewest().id, "new", "stuff", "is", "cool")
            Log.d("help",""+databaseHelper.getNewest().toString())
        }

         */

    }


}
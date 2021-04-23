package com.example.homework4

import android.content.Intent
import android.icu.text.CaseMap
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity



class AddActivity : AppCompatActivity() {
    private lateinit var editTextTitle: EditText
    private lateinit var buttonSave: Button

    private val dreamViewModel:DreamViewModel by viewModels{
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        editTextTitle = findViewById(R.id.edit_title)
        buttonSave = findViewById(R.id.button_save)

        buttonSave.setOnClickListener {
            if (TextUtils.isEmpty(editTextTitle.text)){
                toastError("missing fields")
            }
            else{

                val dream = Dream(editTextTitle.text.toString(), "", "", "")
                dreamViewModel.addDream(dream)

                val intent = Intent(this@AddActivity, MainActivity::class.java)
                startActivity(intent)

            }
        }

    }
    private fun toastError(text:String){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}
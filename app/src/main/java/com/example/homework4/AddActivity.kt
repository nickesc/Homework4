package com.example.homework4

import android.content.Intent
import android.icu.text.CaseMap
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import java.util.*


class AddActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var editTextTitle: EditText
    private lateinit var editTextContent: EditText
    private lateinit var editTextReflection: EditText
    private lateinit var buttonSave: Button
    private lateinit var spinner: Spinner

    //private lateinit var title:String
    //private lateinit var content:String
    //private lateinit var reflection:String
    //private lateinit var emotion:String

    private val dreamViewModel:DreamViewModel by viewModels{
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        editTextTitle = findViewById(R.id.edit_title)
        editTextContent = findViewById(R.id.edit_content)
        editTextReflection = findViewById(R.id.edit_reflection)
        buttonSave = findViewById(R.id.button_save)
        spinner=findViewById(R.id.spinner_emotion)

        val update:Int = intent.getIntExtra("update", -1)

        if (update != -1) {
            dreamViewModel.getDream(update).observe(this, Observer <Dream>{
                dream ->
                editTextTitle.setText(dream.title)
                editTextContent.setText(dream.content)
                editTextReflection.setText(dream.reflection)

            })
            buttonSave.setOnClickListener {
                if (TextUtils.isEmpty(editTextTitle.text) || TextUtils.isEmpty(editTextContent.text) || TextUtils.isEmpty(editTextReflection.text) ){
                    toastError("missing fields")
                }
                else{

                    dreamViewModel.updateDream(update, editTextTitle.text.toString(), editTextContent.text.toString(), editTextReflection.text.toString(), getEmotion(spinner.selectedItemPosition))
                    //dreamViewModel.addDream(dream)
                    val intent=Intent(this@AddActivity, DetailsActivity::class.java)
                    intent.putExtra("id", update)
                    startActivity(intent)

                }
            }
        }


        else {
            buttonSave.setOnClickListener {
                if (TextUtils.isEmpty(editTextTitle.text) || TextUtils.isEmpty(editTextContent.text) || TextUtils.isEmpty(editTextReflection.text) ) {
                    toastError("missing fields")
                } else {

                    val dream = Dream(
                        editTextTitle.text.toString(),
                        editTextContent.text.toString(),
                        editTextReflection.text.toString(),
                        getEmotion(spinner.selectedItemPosition)
                    )
                    dreamViewModel.addDream(dream)
                    finish()

                }
            }
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.emotion_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this

    }
    private fun toastError(text:String){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        getEmotion(position)
    }
    private fun getEmotion(position:Int) : String{
        var emotion=""
        when (position){
            0 -> emotion="fear"
            1 -> emotion="panic"
            2 -> emotion="loss of self"
            3 -> emotion="grief"
            4 -> emotion="freedom"
            5 -> emotion="love"
            6 -> emotion="joy"
            7 -> emotion="vulnerability"
            8 -> emotion="confused"
            9 -> emotion="sad"
        }
        return emotion
    }
    private fun getPosition(emotion:String) : Int{
        var position=0
        when (emotion){
            "fear" -> position=0
            "panic" -> position=1
            "loss of self" -> position=2
            "grief" -> position=3
            "freedom" -> position=4
            "love" -> position=5
            "joy" -> position=6
            "vulnerability" -> position=7
            "confused" -> position=8
            "sad" -> position=9
        }
        return position
    }



}
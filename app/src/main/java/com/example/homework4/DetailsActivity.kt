package com.example.homework4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class DetailsActivity :AppCompatActivity(){

    private lateinit var deleteButton: Button
    private lateinit var updateButton: Button
    private lateinit var dTitleTextView: TextView
    private lateinit var dContentTextView: TextView
    private lateinit var dReflectionTextView: TextView
    private lateinit var dEmotionTextView: TextView

    private val dreamViewModel:DreamViewModel by viewModels{
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        deleteButton=findViewById(R.id.button_delete)
        updateButton=findViewById(R.id.button_update)
        dTitleTextView=findViewById(R.id.textView_dTitle)
        dContentTextView=findViewById(R.id.textView_dContent)
        dReflectionTextView=findViewById(R.id.textView_dReflection)
        dEmotionTextView=findViewById(R.id.textView_dEmotion)

        val dreamId:Int = intent.getIntExtra("id", -1)

        updateButton.setOnClickListener {
            var intent = Intent(this@DetailsActivity, AddActivity::class.java)
            intent.putExtra("update", dreamId)
            startActivity(intent)
        }
        deleteButton.setOnClickListener {
            dreamViewModel.deleteDream(dreamId)
            var intent = Intent(this@DetailsActivity, MainActivity::class.java)
            startActivity(intent)
        }



        if (dreamId==-1){
            finish()
        }

        dreamViewModel.getDream(dreamId).observe(this, Observer <Dream>{
                dream ->
            dTitleTextView.setText(dream.title)
            dContentTextView.setText(dream.content)
            dReflectionTextView.setText(dream.reflection)
            dEmotionTextView.setText(dream.emotion)

        })




    }
}
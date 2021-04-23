package com.example.homework4

import android.util.Log
import kotlinx.coroutines.flow.Flow

class DreamRepository (private val dreamDAO:DreamDAO){

    val allDreams:Flow<List<Dream>> = dreamDAO.getAll()

    suspend fun addDream(dream:Dream){
        return dreamDAO.addDream(dream)
    }

    suspend fun getDream(id:Int): Dream {
        return dreamDAO.getDream(id)
    }

    suspend fun deleteDream(id:Int): Int {
        Log.d("help", ""+id)
        return dreamDAO.deleteDream(id)
    }

    suspend fun updateDream(id:Int, title:String, content:String, reflection:String, emotion:String) : Int{
        return dreamDAO.updateDream(id,title,content,reflection,emotion)
    }

}
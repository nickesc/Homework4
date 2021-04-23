package com.example.homework4

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DreamDAO {
    //dlksfja

    @Query ("SELECT * FROM dream_table ORDER BY id ASC")
    fun getAll() : Flow<List<Dream>>

    @Query ("SELECT * FROM dream_table WHERE id=:id")
    suspend fun getDream(id:Int) : Dream

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDream(dream:Dream)

    @Query("UPDATE dream_table SET title=:title, content=:content, reflection=:reflection, emotion=:emotion WHERE id=:id")
    suspend fun updateDream(id:Int, title:String, content:String, reflection:String, emotion:String) : Int

    @Query("DELETE FROM dream_table WHERE id=:id")
    suspend fun deleteDream(id:Int) : Int


}
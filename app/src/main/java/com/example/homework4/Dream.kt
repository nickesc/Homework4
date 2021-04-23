package com.example.homework4

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//data class Dream (var id:Long, var title:String, var content:String, var reflection:String, var emotion:String)
@Entity(tableName = "dream_table")
class Dream(
                @ColumnInfo(name = "title") var title:String,
                @ColumnInfo(name = "content") var content:String,
                @ColumnInfo(name = "reflection") var reflection:String,
                @ColumnInfo(name = "emotion") var emotion:String){
    @PrimaryKey (autoGenerate = true) @ColumnInfo(name = "id") var id:Int=0
}
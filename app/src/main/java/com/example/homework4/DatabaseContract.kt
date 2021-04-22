package com.example.homework4

import android.provider.BaseColumns

object DatabaseContract{
    object DreamEntry : BaseColumns {
        const val TABLE_NAME = "dream_table"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_CONTENT = "content"
        const val COLUMN_REFLECTION = "reflection"
        const val COLUMN_EMOTION = "emotion"
    }
}

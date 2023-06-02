package com.example.notesaddingapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notes::class], version = 1)
abstract class NotesDatabase:RoomDatabase() {
    abstract fun dao():NotesDAO

    companion object {
        @Volatile
        private var INSTANCE :NotesDatabase?=null
        fun getDatabaseInstance(context: Context):NotesDatabase{
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NotesDatabase::class.java,
                        "notes_db"
                    ).build()
                }
                INSTANCE = instance
                return instance

            }
        }
    }
}
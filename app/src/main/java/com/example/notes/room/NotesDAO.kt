package com.example.notesaddingapp.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDAO {
    @Insert
    suspend fun insertNotes(note:Notes)

    @Delete
    suspend fun deleteNotes(note: Notes)

    @Query("SELECT * FROM notes")
    fun getAllNotes():LiveData<List<Notes>>

    @Update
    suspend fun updateNotes(note: Notes)


}
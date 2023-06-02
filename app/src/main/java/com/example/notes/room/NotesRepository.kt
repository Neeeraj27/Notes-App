package com.example.notesaddingapp.room

import androidx.lifecycle.LiveData

class NotesRepository(val dao:NotesDAO) {
    fun getAllNotes(): LiveData<List<Notes>>{
        return dao.getAllNotes()
    }
    suspend fun insertNotes(note:Notes){
        dao.insertNotes(note)
    }
    suspend fun deleteNotes(note: Notes){
        dao.deleteNotes(note)
    }
    suspend fun updateNotes(note : Notes){
        dao.updateNotes(note)
    }
}


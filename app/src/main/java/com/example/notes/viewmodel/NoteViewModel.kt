package com.example.notesaddingapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesaddingapp.room.Notes
import com.example.notesaddingapp.room.NotesDatabase
import com.example.notesaddingapp.room.NotesRepository
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = NotesRepository(NotesDatabase.getDatabaseInstance(application).dao())

    fun addNotes(note: Notes) = viewModelScope.launch { repository.insertNotes(note) }
    fun getNotes() = repository.getAllNotes()
    fun deleteNotes(note: Notes) = viewModelScope.launch { repository.deleteNotes(note) }
    fun updateNotes(note: Notes) = viewModelScope.launch { repository.updateNotes(note) }
}



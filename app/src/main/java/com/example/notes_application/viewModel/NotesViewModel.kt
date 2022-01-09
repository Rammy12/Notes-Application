package com.example.notes_application.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notes_application.Database.NotesDatabase
import com.example.notes_application.Reposito.NotesRepository
import com.example.notes_application.model.Notes

class NotesViewModel(application: Application) :AndroidViewModel(application){
    val repository:NotesRepository
    init {
        val dao=NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository=NotesRepository(dao)
    }
    fun addNotes(notes:Notes)
    {
        repository.insertNotes(notes)
    }
    fun getNotes():LiveData<List<Notes>> =repository.getAllNotes()
    fun gethighNotes():LiveData<List<Notes>> = repository.gethighNotes()

    fun getmediumNotes():LiveData<List<Notes>> = repository.getmediumNotes()

    fun getlowNotes():LiveData<List<Notes>> = repository.getlowNotes()
    fun deleteNotes(id:Int)
    {
        repository.deleteNotes(id)
    }
    fun updateNotes(notes: Notes)
    {
        repository.updateNote(notes)
    }
}
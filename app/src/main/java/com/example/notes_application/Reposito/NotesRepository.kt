package com.example.notes_application.Reposito

import androidx.lifecycle.LiveData
import com.example.notes_application.Dao.NotesDao
import com.example.notes_application.model.Notes

class NotesRepository(val dao:NotesDao) {

    fun getAllNotes():LiveData<List<Notes>>{
        return dao.getNotes()
    }
    fun gethighNotes():LiveData<List<Notes>> = dao.gethighNotes()

    fun getmediumNotes():LiveData<List<Notes>> = dao.getmediumNotes()

    fun getlowNotes():LiveData<List<Notes>> = dao.getlowNotes()

    fun insertNotes(notes: Notes)
    {
        dao.insertNotes(notes)
    }
    fun deleteNotes(id:Int)
    {
        dao.deleteNotes(id)
    }
    fun updateNote(notes: Notes)
    {
        dao.updateNotes(notes)
    }
}
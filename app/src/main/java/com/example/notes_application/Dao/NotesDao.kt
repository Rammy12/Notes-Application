package com.example.notes_application.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes_application.model.Notes

@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes")
    fun getNotes():LiveData<List<Notes>>


    @Query("SELECT * FROM Notes WHERE priority=3")
    fun gethighNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=2")
    fun getmediumNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=1")
    fun getlowNotes():LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNotes(id:Int)


    @Update
    fun updateNotes(notes: Notes)
}
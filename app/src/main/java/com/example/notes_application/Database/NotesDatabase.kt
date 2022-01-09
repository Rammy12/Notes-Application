package com.example.notes_application.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes_application.Dao.NotesDao
import com.example.notes_application.model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase:RoomDatabase() {
    abstract fun myNotesDao():NotesDao
    companion object
    {
        @Volatile
        var INSTANCE:NotesDatabase?=null
        fun getDatabaseInstance(context: Context):NotesDatabase
        {
            val tempInstace= INSTANCE
            if(tempInstace!=null)
            {
                return tempInstace
            }
            synchronized(this)
            {
                val roomDatabaseInstance=Room.databaseBuilder(context,NotesDatabase::class.java,"Notes").allowMainThreadQueries().build()
                INSTANCE=roomDatabaseInstance
                return roomDatabaseInstance
            }
        }
    }
}
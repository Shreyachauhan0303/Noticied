package com.example.noticied.Database

import android.icu.text.CaseMap.Title
import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import com.example.noticied.Models.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note:Note)

    @Delete
    suspend fun delete(note:Note)
    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes():LiveData<List<Note>>
    @Query("UPDATE notes_table Set title=:title, note=:note WHERE id=:id ")
    suspend fun update(id:Int?, @Nullable title:String?, note: String?)


}
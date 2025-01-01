package com.example.todoapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.domain.ToDo

@Dao
interface TodoDao {
    @Insert
    suspend fun insert(todo: ToDo)

    @Update
    suspend fun update(todo: ToDo)

    @Delete
    suspend fun delete(todo: ToDo)

    @Query("SELECT * FROM todo")
    suspend fun getAll(): List<ToDo>
}
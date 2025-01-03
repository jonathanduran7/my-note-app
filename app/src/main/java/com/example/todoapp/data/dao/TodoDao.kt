package com.example.todoapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.domain.models.ToDo

@Dao
interface TodoDao {
    @Insert
    suspend fun insert(todo: ToDo)

    @Update
    suspend fun update(todo: ToDo)

    @Query("DELETE FROM todo WHERE id = :todoId")
    suspend fun delete(todoId: Int)

    @Query("SELECT * FROM todo")
    suspend fun getAll(): List<ToDo>
}
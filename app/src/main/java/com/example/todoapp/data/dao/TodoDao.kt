package com.example.todoapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.todoapp.domain.models.ToDo
import com.example.todoapp.domain.models.TodoWithCategory

@Dao
interface TodoDao {
    @Insert
    suspend fun insert(todo: ToDo): Long

    @Update
    suspend fun update(todo: ToDo)

    @Query("DELETE FROM todo WHERE id = :todoId")
    suspend fun delete(todoId: Int)

    @Transaction
    @Query("SELECT * FROM todo")
    suspend fun getAll(): List<TodoWithCategory>

    @Query("SELECT * FROM todo where title like '%' || :query || '%'")
    suspend fun search(query: String): List<TodoWithCategory>

    @Query("SELECT * FROM todo ORDER BY id DESC LIMIT 4")
    suspend fun getRecentlyAdded(): List<TodoWithCategory>

    @Query("SELECT * FROM todo WHERE categoryId = :categoryId")
    suspend fun getTodoByCategory(categoryId: Int): List<TodoWithCategory>

    @Query("SELECT * FROM todo WHERE categoryId = :categoryId AND title like '%' || :query || '%'")
    suspend fun searchByCategory(categoryId: Int, query: String): List<TodoWithCategory>
}
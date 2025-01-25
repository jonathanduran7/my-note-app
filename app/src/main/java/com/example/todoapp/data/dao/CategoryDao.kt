package com.example.todoapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.domain.models.Category

@Dao
interface CategoryDao {
    @Insert
    suspend fun insert(category: Category): Long

    @Update
    suspend fun update(category: Category)

    @Query("DELETE FROM category WHERE id = :categoryId")
    suspend fun delete(categoryId: Int)

    @Query("SELECT * FROM category")
    suspend fun getAll(): List<Category>
}
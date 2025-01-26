package com.example.todoapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.data.dao.CategoryDao
import com.example.todoapp.data.dao.TodoDao
import com.example.todoapp.domain.models.Category
import com.example.todoapp.domain.models.ToDo

@Database(entities = [ToDo::class, Category::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TodoDao
    abstract fun categoryDao(): CategoryDao
}
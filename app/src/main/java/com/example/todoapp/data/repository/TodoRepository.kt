package com.example.todoapp.data.repository

import android.util.Log
import com.example.todoapp.data.dao.TodoDao
import com.example.todoapp.domain.models.ToDo

class TodoRepository(private val todoDao: TodoDao) {
    suspend fun insert(todo: ToDo) {
        todoDao.insert(todo)
    }

    suspend fun update(todo: ToDo) {
        todoDao.update(todo)
    }

    suspend fun delete(todo: ToDo) {
        todoDao.delete(todo)
    }

    suspend fun getAll(): List<ToDo> {
        return todoDao.getAll()
    }
}
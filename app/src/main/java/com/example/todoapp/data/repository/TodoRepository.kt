package com.example.todoapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.todoapp.data.dao.TodoDao
import com.example.todoapp.domain.ToDo

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
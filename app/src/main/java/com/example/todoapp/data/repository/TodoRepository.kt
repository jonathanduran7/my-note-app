package com.example.todoapp.data.repository

import android.util.Log
import com.example.todoapp.data.dao.TodoDao
import com.example.todoapp.domain.models.ToDo

class TodoRepository(private val todoDao: TodoDao) {
    suspend fun insert(todo: ToDo): ToDo {
        val id = todoDao.insert(todo)
        return todo.copy(id = id.toInt())
    }

    suspend fun update(todo: ToDo) {
        val todoDatabase = todoDao.getAll().find { it.id == todo.id }
        if (todoDatabase != null) {
            todoDao.update(todo)
        }
    }

    suspend fun delete(todoId: Int) {
        if(todoId != null) {
            todoDao.delete(todoId)
        }
    }

    suspend fun getAll(): List<ToDo> {
        return todoDao.getAll()
    }

    suspend fun search(query: String): List<ToDo> {
        return todoDao.search(query)
    }
}
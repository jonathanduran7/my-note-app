package com.example.todoapp.data.repository

import com.example.todoapp.data.dao.TodoDao
import com.example.todoapp.domain.models.ToDo
import com.example.todoapp.domain.models.TodoWithCategory

class TodoRepository(private val todoDao: TodoDao) {
    suspend fun insert(todo: ToDo): ToDo {
        val id = todoDao.insert(todo)
        return todo.copy(id = id.toInt())
    }

    suspend fun update(todo: ToDo) {
        val todoDatabase = todoDao.getAll().find { it.todo.id == todo.id }
        if (todoDatabase != null) {
            todoDao.update(todo)
        }
    }

    suspend fun delete(todoId: Int) {
        todoDao.delete(todoId)
    }

    suspend fun getAll(): List<TodoWithCategory> {
        return todoDao.getAll()
    }

    suspend fun search(query: String): List<TodoWithCategory> {
        return todoDao.search(query)
    }
}
package com.example.todoapp.ui.todo

import com.example.todoapp.domain.models.ToDo

interface OnTodoDelete {
    fun onTodoDelete(todo: ToDo)
}
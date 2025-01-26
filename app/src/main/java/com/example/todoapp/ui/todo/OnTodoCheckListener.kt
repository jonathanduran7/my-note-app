package com.example.todoapp.ui.todo

import com.example.todoapp.domain.models.ToDo
import com.example.todoapp.domain.models.TodoWithCategory

interface OnTodoCheckListener {
    fun onTodoCheckChanged(todo: TodoWithCategory, isChecked: Boolean)
}
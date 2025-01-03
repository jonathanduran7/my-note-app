package com.example.todoapp.ui.todo

import com.example.todoapp.domain.models.ToDo

interface OnTodoCheckListener {
    fun onTodoCheckChanged(todo: ToDo, isChecked: Boolean)
}
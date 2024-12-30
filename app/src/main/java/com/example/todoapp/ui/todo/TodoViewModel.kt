package com.example.todoapp.ui.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.domain.ToDo

class TodoViewModel : ViewModel() {

    private val _todos = MutableLiveData<List<ToDo>>(
        listOf(
            ToDo(
                id = 1,
                title = "Buy milk",
                isCompleted = true
            ),
            ToDo(
                id = 2,
                title = "Buy eggs",
                isCompleted = false
            ),
            ToDo(
                id = 3,
                title = "Buy bread",
                isCompleted = false
            )
        )
    )
    val todos: LiveData<List<ToDo>> = _todos

    fun add(todo: ToDo) {
        val current = _todos.value.orEmpty().toMutableList()
        current.add(todo)
        _todos.value = current
    }

    // TODO: Implement the ViewModel
}
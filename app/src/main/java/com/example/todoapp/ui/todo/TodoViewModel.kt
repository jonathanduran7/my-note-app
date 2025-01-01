package com.example.todoapp.ui.todo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.repository.TodoRepository
import com.example.todoapp.domain.ToDo
import kotlinx.coroutines.launch

class TodoViewModel(
    private val todoRepository: TodoRepository
) : ViewModel() {

    private val _todos = MutableLiveData<List<ToDo>>(emptyList())
    val todos: LiveData<List<ToDo>> = _todos

    fun add(todo: ToDo) {
        val current = _todos.value.orEmpty().toMutableList()
        current.add(todo)
        _todos.value = current
    }

    init {
        viewModelScope.launch {
            val todos = todoRepository.getAll()
            _todos.value = todos
            Log.i("TodoViewModel", "Todos: $todos")
        }

//        createExampleTodos()

    }

    private fun createExampleTodos() {
        viewModelScope.launch {
            todoRepository.insert(
                ToDo(
                    id = 1,
                    title = "Buy milk",
                    isCompleted = true
                )
            )
            todoRepository.insert(
                ToDo(
                    id = 2,
                    title = "Buy eggs",
                    isCompleted = false
                )
            )
            todoRepository.insert(
                ToDo(
                    id = 3,
                    title = "Buy bread",
                    isCompleted = false
                )
            )
        }
    }

    // TODO: Implement the ViewModel
}
package com.example.todoapp.ui.todo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.repository.TodoRepository
import com.example.todoapp.domain.models.ToDo
import com.example.todoapp.domain.usecases.AddTodoUseCase
import com.example.todoapp.domain.usecases.ListTodosUseCase
import kotlinx.coroutines.launch

class TodoViewModel(
    private val todoRepository: TodoRepository,
    private val listTodosUseCase: ListTodosUseCase,
    private val addTodoUseCase: AddTodoUseCase
) : ViewModel() {

    private val _todos = MutableLiveData<List<ToDo>>(emptyList())
    val todos: LiveData<List<ToDo>> = _todos

    fun add(todo: ToDo) {
        val current = _todos.value.orEmpty().toMutableList()
        current.add(todo)
        _todos.value = current
    }

    fun addTodo(todo: ToDo) {
        viewModelScope.launch {
            addTodoUseCase(todo)
        }
        this.add(todo)
    }

    fun update(todo: ToDo) {
        val current = _todos.value.orEmpty().toMutableList()
        val index = current.indexOfFirst { it.id == todo.id }
        if (index != -1) {
            current[index] = todo
            _todos.value = current
        }

        viewModelScope.launch {
            todoRepository.update(todo)
        }
    }

    fun remove(todo: ToDo) {
        val current = _todos.value.orEmpty().toMutableList()
        current.remove(todo)
        _todos.value = current

        viewModelScope.launch {
            todoRepository.delete(todo.id)
        }
    }

    init {
        viewModelScope.launch {
            val todos = listTodosUseCase()
            _todos.value = todos
            Log.i("TodoViewModel", "Todos: $todos")
        }
    }

    // TODO: Implement the ViewModel
}
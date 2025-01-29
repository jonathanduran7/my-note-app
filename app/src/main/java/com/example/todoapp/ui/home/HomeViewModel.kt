package com.example.todoapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.models.TodoWithCategory
import com.example.todoapp.domain.usecases.GetRecentlyTodoUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val listTodosUseCase: GetRecentlyTodoUseCase
) : ViewModel() {
    private val _todos = MutableLiveData<List<TodoWithCategory>>(emptyList())
    val todos: LiveData<List<TodoWithCategory>> = _todos

    private fun getAll() {
        viewModelScope.launch {
            _todos.value = listTodosUseCase()
            Log.i("HomeViewModel", "getAll: ${_todos.value}")
        }
    }

    init {
        getAll()
    }
}
package com.example.todoapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.models.Category
import com.example.todoapp.domain.models.TodoWithCategory
import com.example.todoapp.domain.usecases.GetRecentlyTodoUseCase
import com.example.todoapp.domain.usecases.category.ListCategoryUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val listTodosUseCase: GetRecentlyTodoUseCase,
    private val listCategoriesUseCase: ListCategoryUseCase
) : ViewModel() {
    private val _todos = MutableLiveData<List<TodoWithCategory>>()
    val todos: LiveData<List<TodoWithCategory>> = _todos

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories


    private fun getAll() {
        viewModelScope.launch {
            _todos.value = listTodosUseCase().toList()
        }
    }

    init {
        getAll()
        getCategories()
    }

    fun refreshTodos() {
        getAll()
    }

    fun getCategories() {
        viewModelScope.launch {
            _categories.value = listCategoriesUseCase().toList()
        }
    }
}
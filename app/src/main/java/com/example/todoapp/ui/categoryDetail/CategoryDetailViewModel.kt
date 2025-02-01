package com.example.todoapp.ui.categoryDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.models.TodoWithCategory
import com.example.todoapp.domain.usecases.GetTodoByCategoryUseCase
import kotlinx.coroutines.launch

class CategoryDetailViewModel(
    private val getTodoByCategoryUseCase: GetTodoByCategoryUseCase
) : ViewModel() {

    private val _todos = MutableLiveData<List<TodoWithCategory>>(emptyList())
    val todos: LiveData<List<TodoWithCategory>> = _todos

        fun getTodoByCategory(categoryId: Int) {
            viewModelScope.launch {
                _todos.value = getTodoByCategoryUseCase(categoryId)
            }
        }
}
package com.example.todoapp.ui.categoryDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.repository.TodoRepository
import com.example.todoapp.domain.models.ToDo
import com.example.todoapp.domain.models.TodoWithCategory
import com.example.todoapp.domain.usecases.AddTodoUseCase
import com.example.todoapp.domain.usecases.GetTodoByCategoryUseCase
import com.example.todoapp.domain.usecases.RemoveTodoUseCase
import com.example.todoapp.domain.usecases.SearchTodoByCategoryUseCase
import com.example.todoapp.domain.usecases.UpdateTodoUseCase
import kotlinx.coroutines.launch

class CategoryDetailViewModel(
    private val getTodoByCategoryUseCase: GetTodoByCategoryUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val removeTodoUseCase: RemoveTodoUseCase,
    private val searchTodoByCategoryUseCase: SearchTodoByCategoryUseCase,
    private val addTodoUseCase: AddTodoUseCase
) : ViewModel() {

    private val _todos = MutableLiveData<List<TodoWithCategory>>(emptyList())
    val todos: LiveData<List<TodoWithCategory>> = _todos

    fun getTodoByCategory(categoryId: Int) {
        viewModelScope.launch {
            _todos.value = getTodoByCategoryUseCase(categoryId)
        }
    }

    fun updateTodoStatus(todo: TodoWithCategory) {
        viewModelScope.launch {
            updateTodoUseCase(todo.todo)
            _todos.value = _todos.value?.map {
                if (it.todo.id == todo.todo.id) {
                    it.copy(todo = it.todo.copy(isCompleted = !it.todo.isCompleted))
                } else {
                    it
                }
            }
        }
    }

    fun removeTodo(todo: TodoWithCategory) {
        viewModelScope.launch {
            removeTodoUseCase(todo.todo.id)
            _todos.value = _todos.value?.filter { it.todo.id != todo.todo.id }
        }
    }

    fun searchByCategory(categoryId: Int, query: String) {
        viewModelScope.launch {
            _todos.value = searchTodoByCategoryUseCase(categoryId, query)
        }
    }

    fun addTodo(todo: ToDo) {
        viewModelScope.launch {
            addTodoUseCase(todo)
            getTodoByCategory(todo.categoryId ?: 0)
        }
    }
}
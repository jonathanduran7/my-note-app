package com.example.todoapp.ui.todo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.repository.TodoRepository
import com.example.todoapp.domain.models.Category
import com.example.todoapp.domain.models.ToDo
import com.example.todoapp.domain.models.TodoWithCategory
import com.example.todoapp.domain.usecases.AddTodoUseCase
import com.example.todoapp.domain.usecases.ListTodosUseCase
import com.example.todoapp.domain.usecases.SearchTodosUseCase
import com.example.todoapp.domain.usecases.category.ListCategoryUseCase
import kotlinx.coroutines.launch

class TodoViewModel(
    private val todoRepository: TodoRepository,
    private val listTodosUseCase: ListTodosUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val searchTodosUseCase: SearchTodosUseCase,
    private val listCagetoriesUseCase: ListCategoryUseCase
) : ViewModel() {

    private val _todos = MutableLiveData<List<TodoWithCategory>>(emptyList())
    val todos: LiveData<List<TodoWithCategory>> = _todos
    private val _categories = MutableLiveData<List<Category>>(emptyList())
    val categories: LiveData<List<Category>> = _categories


    fun addTodo(todo: ToDo) {
        viewModelScope.launch {
            addTodoUseCase(todo)
            getAll()
        }
    }

    fun update(todo: ToDo) {
        val current = _todos.value.orEmpty().toMutableList()
        val index = current.indexOfFirst { it.todo.id == todo.id }
        if (index != -1) {
            current[index].todo = todo
            _todos.value = current
        }

        viewModelScope.launch {
            todoRepository.update(todo)
        }
    }

    fun remove(todo: ToDo) {
        viewModelScope.launch {
            todoRepository.delete(todo.id)
            getAll()
        }
    }

    init {
        viewModelScope.launch {
            val listTodo = listTodosUseCase()
            _todos.value = listTodo
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            val todos = searchTodosUseCase(query)
            _todos.value = todos
        }
    }

    fun getAll() {
        viewModelScope.launch {
            val todos = todoRepository.getAll()
            _todos.value = todos
        }
    }

    fun getAllCategories() {
        viewModelScope.launch {
            val categories = listCagetoriesUseCase()
            _categories.value = categories
        }
    }
}
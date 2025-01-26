package com.example.todoapp.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.models.Category
import com.example.todoapp.domain.usecases.category.AddCategoryUseCase
import com.example.todoapp.domain.usecases.category.ListCategoryUseCase
import com.example.todoapp.domain.usecases.category.RemoveCategoryUseCase
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val addCategoryUseCase: AddCategoryUseCase,
    private val listCategoryUseCase: ListCategoryUseCase,
    private val removeCategoryUseCase: RemoveCategoryUseCase
) : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>(emptyList())
    val categories: LiveData<List<Category>> = _categories

    init {
       getAll()
    }

    fun addCategory(category: Category) {
        viewModelScope.launch {
            val addedCategory = addCategoryUseCase(category)

            val current = _categories.value.orEmpty().toMutableList()
            current.add(addedCategory)
            _categories.value = current
        }
    }

    fun getAll(){
        viewModelScope.launch {
            val categories = listCategoryUseCase()
            _categories.value = categories
        }
    }

    fun removeCategory(category: Category) {
        viewModelScope.launch {
            removeCategoryUseCase(category.id)
            val current = _categories.value.orEmpty().toMutableList()
            current.remove(category)
            _categories.value = current
        }
    }
}
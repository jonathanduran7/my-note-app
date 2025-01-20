package com.example.todoapp.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.domain.models.Category
import com.example.todoapp.domain.models.ToDo

class CategoryViewModel : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>(emptyList())
    val categories: LiveData<List<Category>> = _categories

    init {
        _categories.value = listOf(
            Category(1, "Work"),
            Category(2, "Personal"),
            Category(3, "Shopping")
        )
    }

}
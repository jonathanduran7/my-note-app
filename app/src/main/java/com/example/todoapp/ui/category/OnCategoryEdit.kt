package com.example.todoapp.ui.category

import com.example.todoapp.domain.models.Category

interface OnCategoryEdit {
    fun onCategoryEdit(category: Category)
}
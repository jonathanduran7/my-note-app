package com.example.todoapp.ui.category

import com.example.todoapp.domain.models.Category

interface OnCategoryDelete {
    fun onCategoryDelete(category: Category)
}
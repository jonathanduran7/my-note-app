package com.example.todoapp.ui.home

import com.example.todoapp.domain.models.Category

interface OnCategoryClick {
    fun onCategoryClick(category: Category)
}
package com.example.todoapp.domain.usecases.category

import com.example.todoapp.data.repository.CategoryRepository
import com.example.todoapp.domain.models.Category

class ListCategoryUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(): List<Category> {
        return categoryRepository.getAll()
    }
}
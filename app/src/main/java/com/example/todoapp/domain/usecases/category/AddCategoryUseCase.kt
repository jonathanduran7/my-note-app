package com.example.todoapp.domain.usecases.category

import com.example.todoapp.data.repository.CategoryRepository
import com.example.todoapp.domain.models.Category

class AddCategoryUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(category: Category): Category {
        return categoryRepository.insert(category)
    }
}
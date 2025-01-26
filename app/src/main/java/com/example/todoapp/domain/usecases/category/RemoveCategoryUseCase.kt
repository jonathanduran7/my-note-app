package com.example.todoapp.domain.usecases.category

import com.example.todoapp.data.repository.CategoryRepository

class RemoveCategoryUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(categoryId: Int) {
        categoryRepository.delete(categoryId)
    }
}
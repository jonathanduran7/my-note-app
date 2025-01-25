package com.example.todoapp.domain.usecases.category

import com.example.todoapp.data.repository.CategoryRepository

class ListCategoryUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke() = categoryRepository.getAll()
}
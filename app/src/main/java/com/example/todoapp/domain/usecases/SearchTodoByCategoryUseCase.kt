package com.example.todoapp.domain.usecases

import com.example.todoapp.data.repository.TodoRepository
import com.example.todoapp.domain.models.TodoWithCategory

class SearchTodoByCategoryUseCase(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(categoryId: Int, query: String): List<TodoWithCategory> {
        return todoRepository.searchByCategory(categoryId, query)
    }
}
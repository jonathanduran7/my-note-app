package com.example.todoapp.domain.usecases

import com.example.todoapp.data.repository.TodoRepository

class GetTodoByCategoryUseCase(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(categoryId: Int) = todoRepository.getTodoByCategory(categoryId)
}
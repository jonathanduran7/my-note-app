package com.example.todoapp.domain.usecases

import com.example.todoapp.data.repository.TodoRepository
import com.example.todoapp.domain.models.TodoWithCategory

class GetTodoByCategoryUseCase(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(categoryId: Int): List<TodoWithCategory> {
        return todoRepository.getTodoByCategory(categoryId)
    }
}
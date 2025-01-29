package com.example.todoapp.domain.usecases

import com.example.todoapp.data.repository.TodoRepository
import com.example.todoapp.domain.models.TodoWithCategory

class GetRecentlyTodoUseCase(
   private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(): List<TodoWithCategory> {
        return todoRepository.getRecentlyAdded()
    }
}
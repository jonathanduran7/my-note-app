package com.example.todoapp.domain.usecases

import com.example.todoapp.data.repository.TodoRepository

class RemoveTodoUseCase(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(todoId: Int) = todoRepository.delete(todoId)
}
package com.example.todoapp.domain.usecases

import com.example.todoapp.data.repository.TodoRepository

class ListTodosUseCase(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke() = todoRepository.getAll()
}

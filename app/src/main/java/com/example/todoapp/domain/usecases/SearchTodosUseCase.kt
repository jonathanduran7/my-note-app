package com.example.todoapp.domain.usecases

import com.example.todoapp.data.repository.TodoRepository
import com.example.todoapp.domain.models.ToDo

class SearchTodosUseCase(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(query: String): List<ToDo> {
        return todoRepository.search(query)
    }
}
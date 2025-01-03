package com.example.todoapp.domain.usecases

import com.example.todoapp.data.repository.TodoRepository
import com.example.todoapp.domain.models.ToDo

class UpdateTodoUseCase(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(todo: ToDo) = todoRepository.update(todo)
}
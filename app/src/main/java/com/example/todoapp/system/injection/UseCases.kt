package com.example.todoapp.system.injection

import com.example.todoapp.domain.usecases.AddTodoUseCase
import com.example.todoapp.domain.usecases.ListTodosUseCase
import com.example.todoapp.domain.usecases.RemoveTodoUseCase
import com.example.todoapp.domain.usecases.SearchTodosUseCase
import com.example.todoapp.domain.usecases.UpdateTodoUseCase
import org.koin.dsl.module

val usecases = module {
    factory { ListTodosUseCase(get()) }
    factory { AddTodoUseCase(get()) }
    factory { SearchTodosUseCase(get()) }
    factory { RemoveTodoUseCase(get()) }
    factory { UpdateTodoUseCase(get()) }
}
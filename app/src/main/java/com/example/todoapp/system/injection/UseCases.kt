package com.example.todoapp.system.injection

import com.example.todoapp.domain.usecases.AddTodoUseCase
import com.example.todoapp.domain.usecases.GetRecentlyTodoUseCase
import com.example.todoapp.domain.usecases.GetTodoByCategoryUseCase
import com.example.todoapp.domain.usecases.ListTodosUseCase
import com.example.todoapp.domain.usecases.RemoveTodoUseCase
import com.example.todoapp.domain.usecases.SearchTodosUseCase
import com.example.todoapp.domain.usecases.UpdateTodoUseCase
import com.example.todoapp.domain.usecases.category.AddCategoryUseCase
import com.example.todoapp.domain.usecases.category.ListCategoryUseCase
import com.example.todoapp.domain.usecases.category.RemoveCategoryUseCase
import com.example.todoapp.domain.usecases.category.UpdateCategoryUseCase
import org.koin.dsl.module

val usecases = module {
    factory { ListTodosUseCase(get()) }
    factory { AddTodoUseCase(get()) }
    factory { SearchTodosUseCase(get()) }
    factory { RemoveTodoUseCase(get()) }
    factory { UpdateTodoUseCase(get()) }
    factory { AddCategoryUseCase(get()) }
    factory { ListCategoryUseCase(get()) }
    factory { RemoveCategoryUseCase(get()) }
    factory { UpdateCategoryUseCase(get()) }
    factory { GetRecentlyTodoUseCase(get()) }
    factory { GetTodoByCategoryUseCase(get()) }
}
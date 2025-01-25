package com.example.todoapp.system.injection

import com.example.todoapp.data.repository.CategoryRepository
import com.example.todoapp.data.repository.TodoRepository
import org.koin.dsl.module

val respositoriesModule = module {
    single {
        TodoRepository(get())
    }
    single {
        CategoryRepository(get())
    }
}
package com.example.todoapp.system.injection

import androidx.room.Room
import com.example.todoapp.data.database.AppDatabase
import com.example.todoapp.data.repository.TodoRepository
import com.example.todoapp.ui.todo.TodoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {
    viewModel { TodoViewModel(get(), get(), get(), get()) }
}
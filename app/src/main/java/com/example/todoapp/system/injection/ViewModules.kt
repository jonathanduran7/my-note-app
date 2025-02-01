package com.example.todoapp.system.injection

import com.example.todoapp.ui.category.CategoryViewModel
import com.example.todoapp.ui.categoryDetail.CategoryDetailViewModel
import com.example.todoapp.ui.home.HomeViewModel
import com.example.todoapp.ui.todo.TodoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {
    viewModel { TodoViewModel(get(), get(), get(), get(), get()) }
    viewModel { CategoryViewModel(get(), get(), get(), get()) }
    viewModel { HomeViewModel(get(),get(), get(), get()) }
    viewModel { CategoryDetailViewModel(get(), get(), get(), get(),get()) }
}
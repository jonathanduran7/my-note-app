package com.example.todoapp.system.injection

import androidx.room.Room
import com.example.todoapp.data.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "tasks_database"
        ).build()
    }
    single { get<AppDatabase>().taskDao() }
    single { get<AppDatabase>().categoryDao() }
}
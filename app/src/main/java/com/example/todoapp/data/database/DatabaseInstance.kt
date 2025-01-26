package com.example.todoapp.data.database

import android.content.Context
import androidx.room.Room
import com.example.todoapp.data.database.migrations.MIGRATION_1_2
import com.example.todoapp.data.database.migrations.MIGRATION_2_3

object DatabaseInstance {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "tasks_database"
            )
                .addMigrations(MIGRATION_1_2,MIGRATION_2_3)
                .build()
            INSTANCE = instance
            instance
        }
    }
}
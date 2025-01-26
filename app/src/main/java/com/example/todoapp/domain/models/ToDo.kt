package com.example.todoapp.domain.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "todo",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val isCompleted: Boolean,

    val categoryId: Int?
)
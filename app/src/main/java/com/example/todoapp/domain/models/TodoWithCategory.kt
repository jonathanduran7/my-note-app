package com.example.todoapp.domain.models

import androidx.room.Embedded
import androidx.room.Relation

data class TodoWithCategory(
    @Embedded var todo: ToDo,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: Category?
)
package com.example.todoapp.domain.models

import androidx.room.Entity

@Entity(tableName = "category")
data class Category(
    val id: Int,
    val name: String
)
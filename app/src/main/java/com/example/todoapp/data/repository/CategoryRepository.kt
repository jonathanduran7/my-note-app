package com.example.todoapp.data.repository

import com.example.todoapp.data.dao.CategoryDao
import com.example.todoapp.domain.models.Category

class CategoryRepository(private val categoryDao: CategoryDao) {
    suspend fun insert(category: Category): Category {
        val id = categoryDao.insert(category)
        return category.copy(id = id.toInt())
    }

    suspend fun update(category: Category) {
        val categoryDatabase = categoryDao.getAll().find { it.id == category.id }
        if (categoryDatabase != null) {
            categoryDao.update(category)
        }
    }

    suspend fun delete(categoryId: Int) {
        categoryDao.delete(categoryId)
    }

    suspend fun getAll(): List<Category> {
        return categoryDao.getAll()
    }
}
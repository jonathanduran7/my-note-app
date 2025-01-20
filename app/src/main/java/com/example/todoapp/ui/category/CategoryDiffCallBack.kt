package com.example.todoapp.ui.category

import androidx.recyclerview.widget.DiffUtil
import com.example.todoapp.domain.models.Category

class CategoryDiffCallBack: DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}
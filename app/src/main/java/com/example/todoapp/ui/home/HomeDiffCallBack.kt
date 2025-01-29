package com.example.todoapp.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.example.todoapp.domain.models.TodoWithCategory

class HomeDiffCallBack: DiffUtil.ItemCallback<TodoWithCategory>() {
    override fun areItemsTheSame(oldItem: TodoWithCategory, newItem: TodoWithCategory): Boolean {
        return oldItem.todo.id == newItem.todo.id
    }

    override fun areContentsTheSame(oldItem: TodoWithCategory, newItem: TodoWithCategory): Boolean {
        return oldItem == newItem
    }
}
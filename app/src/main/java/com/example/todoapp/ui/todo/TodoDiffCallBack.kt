package com.example.todoapp.ui.todo

import androidx.recyclerview.widget.DiffUtil
import com.example.todoapp.domain.models.ToDo

class TodoDiffCallBack: DiffUtil.ItemCallback<ToDo>() {
    override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
        return oldItem == newItem
    }
}
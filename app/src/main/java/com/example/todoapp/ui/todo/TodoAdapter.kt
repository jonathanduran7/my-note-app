package com.example.todoapp.ui.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.todoapp.R
import com.example.todoapp.domain.models.ToDo

class TodoAdapter(
    private val listener: OnTodoCheckListener
): ListAdapter<ToDo, TodoViewHolder>(TodoDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = getItem(position)
        holder.bind(todo, listener)
    }
}
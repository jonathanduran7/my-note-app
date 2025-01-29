package com.example.todoapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.todoapp.R
import com.example.todoapp.domain.models.TodoWithCategory
import com.example.todoapp.ui.todo.OnTodoCheckListener
import com.example.todoapp.ui.todo.OnTodoDelete

class HomeAdapter(
    private val listener: OnTodoCheckListener,
    private val deleteListener: OnTodoDelete
): ListAdapter<TodoWithCategory, HomeViewHolder>(HomeDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val todo = getItem(position)
        holder.bind(todo, listener, deleteListener)
    }
}
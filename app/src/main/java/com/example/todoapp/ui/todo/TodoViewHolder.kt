package com.example.todoapp.ui.todo

import android.view.View
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.domain.models.ToDo

class TodoViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val checkBox: CheckBox = view.findViewById<CheckBox>(R.id.checkBox)

    fun bind(todo: ToDo, listener: OnTodoCheckListener) {
        checkBox.setOnCheckedChangeListener(null)

        checkBox.isChecked = todo.isCompleted
        checkBox.text = todo.title

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            listener.onTodoCheckChanged(todo, isChecked)
        }
    }
}
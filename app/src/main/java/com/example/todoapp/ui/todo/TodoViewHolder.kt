package com.example.todoapp.ui.todo

import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.domain.models.ToDo
import com.example.todoapp.domain.models.TodoWithCategory

class TodoViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val checkBox: CheckBox = view.findViewById<CheckBox>(R.id.checkBox)
    private val deleteButton: Button = view.findViewById<Button>(R.id.deleteButton)
    private val descriptionCategory: TextView = view.findViewById<TextView>(R.id.descriptionCategory)

    fun bind(todo: TodoWithCategory, listener: OnTodoCheckListener, deleteListener: OnTodoDelete) {
        checkBox.setOnCheckedChangeListener(null)

        checkBox.isChecked = todo.todo.isCompleted
        checkBox.text = todo.todo.title
        descriptionCategory.text = todo.category?.name?: "No category"

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            listener.onTodoCheckChanged(todo, isChecked)
        }

        deleteButton.setOnClickListener {
            deleteListener.onTodoDelete(todo)
        }
    }
}
package com.example.todoapp.ui.todo

import androidx.recyclerview.widget.DiffUtil
import com.example.todoapp.domain.ToDo

class TodoDiffCallBack (
    private val oldList: List<ToDo>,
    private val newList: List<ToDo>
): DiffUtil.Callback(){
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
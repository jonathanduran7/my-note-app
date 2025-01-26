package com.example.todoapp.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.todoapp.domain.models.Category
import com.example.todoapp.R

class CategoryAdapter(
    private val onDeleteListener: OnCategoryDelete,
    private val onEditListener: OnCategoryEdit
): ListAdapter<Category, CategoryViewHolder>(CategoryDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cateogry, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position), onDeleteListener, onEditListener)
    }
}
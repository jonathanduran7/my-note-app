package com.example.todoapp.ui.home

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.domain.models.Category

class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val categoryName: TextView = view.findViewById<TextView>(R.id.categoryNameCard)
    private val cardCategory: View = view.findViewById<View>(R.id.cardCategory)

    fun bind(category: Category, listener: OnCategoryClick) {
        categoryName.text = category.name

        cardCategory.setOnClickListener {
            listener.onCategoryClick(category)
        }
    }
}
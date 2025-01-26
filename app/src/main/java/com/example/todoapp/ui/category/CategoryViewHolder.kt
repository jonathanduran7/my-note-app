package com.example.todoapp.ui.category

import android.app.Dialog
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.domain.models.Category

class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val deleteBtn: Button = view.findViewById<Button>(R.id.deleteCategoryBtn)
    private val editBtn: Button = view.findViewById<Button>(R.id.updateCategoryBtn)
    private val categoryName: TextView = view.findViewById<TextView>(R.id.categoryName)

    fun bind(category: Category, onDeleteListener: OnCategoryDelete, onEditListener: OnCategoryEdit){
        categoryName.text = category.name

        deleteBtn.setOnClickListener {
            onDeleteListener.onCategoryDelete(category)
        }

        editBtn.setOnClickListener {
            setupCategoryDialog(
                onEditListener,
                category
            )
        }
    }

    private fun setupCategoryDialog(
        onEditListener: OnCategoryEdit,
        category: Category
    ){
        val dialog = Dialog(itemView.context)
        dialog.setContentView(R.layout.dialog_category)
        dialog.setCancelable(true)

        dialog.findViewById<TextView>(R.id.textViewTitle).text = "Editar Categoria"
        dialog.findViewById<Button>(R.id.buttonSaveCategory).text = "Editar"

        val categoryName = dialog.findViewById<TextView>(R.id.editTextCategory)
        categoryName.text = this.categoryName.text

        if (categoryName.text.isEmpty()) {
            return
        }

        dialog.findViewById<Button>(R.id.buttonSaveCategory).setOnClickListener {
            val category = Category(
                id = category.id,
                name = categoryName.text.toString()
            )
            onEditListener.onCategoryEdit(category)
            dialog.dismiss()
        }

        dialog.show()
    }
}
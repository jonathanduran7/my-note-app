package com.example.todoapp.ui.category

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentCategoryBinding
import com.example.todoapp.domain.models.Category
import org.koin.android.ext.android.inject

class CategoryFragment : Fragment(), OnCategoryDelete, OnCategoryEdit {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CategoryViewModel by inject()
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupDialog()
    }

    private fun setupRecyclerView() {
        categoryAdapter = CategoryAdapter(
            this,
            this
        )
        binding.rvCategory.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        viewModel.categories.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it)
        }
    }

    private fun setupDialog(){
        binding.addCategoryBtn.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.dialog_category)
            dialog.setCancelable(true)

            val buttonSaveCategory = dialog.findViewById<Button>(R.id.buttonSaveCategory)
            val categoryName = dialog.findViewById<EditText>(R.id.editTextCategory)

            buttonSaveCategory.setOnClickListener {
                if (categoryName.text.toString().isEmpty()) {
                    return@setOnClickListener
                }

                val category = Category(name = categoryName.text.toString())
                viewModel.addCategory(category)
                categoryAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCategoryDelete(category: Category) {
        showConfirmationDialog(category)
    }

    override fun onCategoryEdit(category: Category) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_category)
        dialog.setCancelable(true)

        dialog.findViewById<TextView>(R.id.textViewTitle).text = "Editar Categoria"
        dialog.findViewById<Button>(R.id.buttonSaveCategory).text = "Editar"

        dialog.findViewById<EditText>(R.id.editTextCategory).setText(category.name)

        dialog.findViewById<Button>(R.id.buttonSaveCategory).setOnClickListener {
            val categoryName = dialog.findViewById<EditText>(R.id.editTextCategory)
            if (categoryName.text.toString().isEmpty()) {
                return@setOnClickListener
            }

            val category = Category(
                id = category.id,
                name = categoryName.text.toString()
            )
            viewModel.updateCategory(category)
            categoryAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showConfirmationDialog(
        category: Category
    ){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirmacion")
        builder.setMessage("¿Estas seguro de eliminar esta categoria? Todos los todos asociados a esta categoria seran eliminados.")

        builder.setPositiveButton("Si") { dialog, which ->
            viewModel.removeCategory(category)
            categoryAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }

        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
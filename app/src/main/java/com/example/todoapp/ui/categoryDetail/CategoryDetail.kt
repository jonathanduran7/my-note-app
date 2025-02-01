package com.example.todoapp.ui.categoryDetail

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentCategoryDetailBinding

class CategoryDetail : Fragment() {

    companion object {
        const val CATEGORY_ID = "categoryId"
        const val CATEGORY_NAME = "categoryName"
    }

    private val viewModel: CategoryDetailViewModel by viewModels()
    private var _binding: FragmentCategoryDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryId = requireArguments().getInt(CATEGORY_ID)
        val categoryName = requireArguments().getString(CATEGORY_NAME)

        binding.categoryNameTitle.text = categoryName
    }
}
package com.example.todoapp.ui.categoryDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.FragmentCategoryDetailBinding
import com.example.todoapp.domain.models.TodoWithCategory
import com.example.todoapp.ui.todo.OnTodoCheckListener
import com.example.todoapp.ui.todo.OnTodoDelete
import com.example.todoapp.ui.todo.TodoAdapter
import org.koin.android.ext.android.inject

class CategoryDetail : Fragment(), OnTodoCheckListener, OnTodoDelete {

    companion object {
        const val CATEGORY_ID = "categoryId"
        const val CATEGORY_NAME = "categoryName"
    }

    private val viewModel: CategoryDetailViewModel by inject()
    private var _binding: FragmentCategoryDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var todoAdapter: TodoAdapter

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
        viewModel.getTodoByCategory(categoryId)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        todoAdapter = TodoAdapter(this, this)

        binding.categoryDetailRecyclerView.apply {
            setHasFixedSize(true)
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.todos.observe(viewLifecycleOwner, Observer { todos ->
            todoAdapter.submitList(todos){
                binding.categoryDetailRecyclerView.scrollToPosition(0)
            }
        })

        binding.categoryDetailRecyclerView.post {
            binding.categoryDetailRecyclerView.requestLayout()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onTodoCheckChanged(todo: TodoWithCategory, isChecked: Boolean) {
//        todo.todo.isCompleted = isChecked
//        viewModel.update(todo)
    }

    override fun onTodoDelete(todo: TodoWithCategory) {}
}
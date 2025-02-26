package com.example.todoapp.ui.home


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentHomeBinding
import com.example.todoapp.domain.models.Category
import com.example.todoapp.domain.models.TodoWithCategory
import com.example.todoapp.ui.categoryDetail.CategoryDetail
import com.example.todoapp.ui.todo.OnTodoCheckListener
import com.example.todoapp.ui.todo.OnTodoDelete
import org.koin.android.ext.android.inject

class HomeFragment : Fragment(), OnTodoCheckListener, OnTodoDelete, OnCategoryClick {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by inject()
    private lateinit var homeAdapter : HomeAdapter
    private var categoryAdapter: CategoryAdapter = CategoryAdapter(this)

    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupCategories()
        setupListeners()
        setupEmptyState()
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.refreshTodos()
        homeViewModel.getCategories()
    }

    private fun setupRecyclerView() {
        homeAdapter = HomeAdapter(
            this,
            this
        )

        binding.recyclerViewHome.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        homeViewModel.todos.observe(viewLifecycleOwner, Observer { todos ->
            homeAdapter.submitList(todos){
                binding.recyclerViewHome.scrollToPosition(0)
            }
        })

        binding.recyclerViewHome.post {
            binding.recyclerViewHome.requestLayout()
        }
    }

    private fun setupListeners() {
        binding.buttonViewMore.setOnClickListener {
            navigateToCreateTodo()
        }
    }

    override fun onTodoCheckChanged(todo: TodoWithCategory, isChecked: Boolean) {
        val updatedTodo = todo.todo.copy(isCompleted = isChecked)
        homeViewModel.updateTodo(updatedTodo)
    }

    override fun onTodoDelete(todo: TodoWithCategory) {
        homeViewModel.remove(todo.todo)
    }

    private fun navigateToCreateTodo() {
        navController.navigate(R.id.todoFragment)
    }

    private fun setupCategories(){
        binding.recyclerViewCategories.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        homeViewModel.categories.observe(viewLifecycleOwner, Observer { categories ->
            categoryAdapter.submitList(categories) {
                binding.recyclerViewCategories.scrollToPosition(0)
            }
        })

        binding.recyclerViewCategories.post {
            binding.recyclerViewCategories.requestLayout()
        }
    }

    private fun setupEmptyState(){
        homeViewModel.todos.observe(viewLifecycleOwner, Observer { todos ->
            if (todos.isEmpty()) {
                binding.noTodoHome.visibility = View.VISIBLE
                binding.recyclerViewHome.visibility = View.GONE
                binding.buttonViewMore.visibility = View.GONE
                binding.textViewHome.visibility = View.GONE
            } else {
                binding.noTodoHome.visibility = View.GONE
                binding.recyclerViewHome.visibility = View.VISIBLE
                binding.textViewHome.visibility = View.VISIBLE
                binding.buttonViewMore.visibility = View.VISIBLE
            }
        })
    }

    override fun onCategoryClick(category: Category) {
        val bundle = Bundle().apply {
            putInt(CategoryDetail.CATEGORY_ID, category.id)
            putString(CategoryDetail.CATEGORY_NAME, category.name)
        }
        navController.navigate(R.id.categoryDetail, bundle)
    }
}
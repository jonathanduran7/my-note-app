package com.example.todoapp.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentHomeBinding
import com.example.todoapp.domain.models.Category
import com.example.todoapp.domain.models.TodoWithCategory
import com.example.todoapp.ui.todo.OnTodoCheckListener
import com.example.todoapp.ui.todo.OnTodoDelete
import org.koin.android.ext.android.inject

class HomeFragment : Fragment(), OnTodoCheckListener, OnTodoDelete {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by inject()
    private lateinit var homeAdapter : HomeAdapter
    private var categoryAdapter: CategoryAdapter = CategoryAdapter()

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
            homeAdapter.submitList(todos.toList())
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

    override fun onTodoCheckChanged(todo: TodoWithCategory, isChecked: Boolean) {}

    override fun onTodoDelete(todo: TodoWithCategory) {}

    private fun navigateToCreateTodo() {
        navController.navigate(R.id.todoFragment)
    }

    private fun setupCategories(){
        //TODO: Replace this with actual data
        val items = listOf(
            Category(1, "Personal"),
            Category(2, "Work"),
            Category(3, "Home"),
            Category(4, "School"),
            Category(5, "Other")
        )

        binding.recyclerViewCategories.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        categoryAdapter.submitList(items)
        categoryAdapter.notifyDataSetChanged()
    }
}
package com.example.todoapp.ui.todo

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentTodoBinding
import com.example.todoapp.domain.models.ToDo
import org.koin.android.ext.android.inject

class TodoFragment : Fragment(), OnTodoCheckListener, OnTodoDelete {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TodoViewModel by inject()
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearch()
        setupDialog()
        setupEmptyState()
    }

    private fun setupSearch() {
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("TodoFragment", "Query: $query")
                viewModel.search(query.orEmpty())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText.isNullOrEmpty()) {
                    viewModel.getAll()
                }
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        todoAdapter = TodoAdapter(
            listener = this,
            deleteListener = this
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = todoAdapter
            setHasFixedSize(true)
        }

        viewModel.todos.observe(viewLifecycleOwner, Observer { todos ->
            todoAdapter.submitList(todos)
        })
    }

    override fun onTodoCheckChanged(todo: ToDo, isChecked: Boolean) {
        val updatedTodo = todo.copy(isCompleted = isChecked)
        viewModel.update(updatedTodo)
        todoAdapter.notifyDataSetChanged()
    }

    override fun onTodoDelete(todo: ToDo) {
        viewModel.remove(todo)
        todoAdapter.notifyDataSetChanged()
    }

    private fun setupEmptyState(){
        //if there are no todos, show the message "No todos" in the center of the screen
        viewModel.todos.observe(viewLifecycleOwner, Observer { todos ->
            if (todos.isEmpty()) {
                binding.noTodos.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
                binding.searchView.visibility = View.GONE
            } else {
                binding.noTodos.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                binding.searchView.visibility = View.VISIBLE
            }
        })
    }

    private fun setupDialog() {
        binding.addButton.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.dialog_todo)

            dialog.setCancelable(true)

            val editTextTodo = dialog.findViewById<EditText>(R.id.editTextTodo)
            val buttonAddTodo = dialog.findViewById<Button>(R.id.buttonAddTodo)

            buttonAddTodo.setOnClickListener {
                val todoText = editTextTodo.text.toString()

                val todo = ToDo(
                    title = todoText,
                    isCompleted = false
                )

                viewModel.addTodo(todo)

                dialog.dismiss()
            }

            dialog.show()
        }
    }
}
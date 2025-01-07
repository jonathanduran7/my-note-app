package com.example.todoapp.ui.todo

import android.app.Dialog
import androidx.fragment.app.viewModels
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
import com.example.todoapp.data.database.DatabaseInstance
import com.example.todoapp.data.repository.TodoRepository
import com.example.todoapp.databinding.FragmentTodoBinding
import com.example.todoapp.domain.models.ToDo
import com.example.todoapp.domain.usecases.AddTodoUseCase
import com.example.todoapp.domain.usecases.ListTodosUseCase

class TodoFragment : Fragment(), OnTodoCheckListener, OnTodoDelete {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TodoViewModel
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

        //TODO: refactor this
        val todoDao = DatabaseInstance.getDatabase(requireContext()).taskDao()
        val todoRepository = TodoRepository(todoDao)
        val listTodosUseCase = ListTodosUseCase(todoRepository)
        val addTodoUseCase = AddTodoUseCase(todoRepository)

        viewModel = TodoViewModel(todoRepository, listTodosUseCase, addTodoUseCase)

        viewModel.todos.observe(viewLifecycleOwner, Observer { todos ->
            todoAdapter.submitList(todos)
        })

        //TODO: REFACTOR THIS
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

    override fun onTodoCheckChanged(todo: ToDo, isChecked: Boolean) {
        val updatedTodo = todo.copy(isCompleted = isChecked)
        viewModel.update(updatedTodo)
        todoAdapter.notifyDataSetChanged()
    }

    override fun onTodoDelete(todo: ToDo) {
        viewModel.remove(todo)
        todoAdapter.notifyDataSetChanged()
    }
}
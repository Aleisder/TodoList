package com.example.todolist.screens.todolist

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.data.Todo
import com.example.todolist.databinding.FragmentTodoListBinding
import com.example.todolist.viewmodel.TodoListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoListFragment : Fragment(), TodoAdapter.OnItemClickListener {

    private lateinit var binding: FragmentTodoListBinding
    private val viewModel: TodoListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onItemClick(todo: Todo) {
        viewModel.onTodoSelected(todo)
    }

    override fun onCheckboxClick(todo: Todo, isChecked: Boolean) {
        viewModel.onTodoCheckedChanged(todo, isChecked)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val todoAdapter = TodoAdapter(this)

        binding.rvTodoList.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        viewModel.todos.observe(viewLifecycleOwner) {
            todoAdapter.submitList(it)
        }

        binding.btAddTodo.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_todoListFragment_to_addNewTodoFragment)
        }

        binding.svSearchTodo.apply {
            setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    clearFocus()
                    if (!query.isNullOrEmpty()) {
                        viewModel.searchTodos("%$query%").observe(viewLifecycleOwner) {
                            todoAdapter.submitList(it)
                        }
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (!newText.isNullOrEmpty()) {
                        viewModel.searchTodos("%$newText%").observe(viewLifecycleOwner) {
                            todoAdapter.submitList(it)
                        }
                    }
                    return true
                }
            })
        }
    }


}
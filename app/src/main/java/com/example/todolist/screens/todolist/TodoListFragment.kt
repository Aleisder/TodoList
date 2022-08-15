package com.example.todolist.screens.todolist

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.databinding.FragmentTodoListBinding
import com.example.todolist.viewmodel.TodoListViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "TodoListFragment"

@AndroidEntryPoint
class TodoListFragment : Fragment() {

    private lateinit var binding: FragmentTodoListBinding
    private val viewModel: TodoListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setting up the RecyclerView
        val todoAdapter = TodoAdapter()
        binding.rvTodoList.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        //todos from viewModel are shown in RecyclerView
        viewModel.todos.observe(viewLifecycleOwner) {
            todoAdapter.submitList(it)
            binding.tvCountOfTodos.text = it.size.toString()
        }



        binding.btAddTodo.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_todoListFragment_to_addNewTodoFragment)
        }


        binding.svSearchTodo.apply {
            setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d(TAG, "${todoAdapter.itemCount}")
                    viewModel.searchTodos("%$query%").observe(viewLifecycleOwner) {
                        todoAdapter.submitList(it)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    onQueryTextSubmit(newText)
                    return true
                }
            })
        }

    }


}
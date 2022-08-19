package com.example.todolist.screens.todolist.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.FragmentTodoListBinding
import com.example.todolist.screens.todolist.viewmodel.TodoListViewModel
import com.google.android.material.snackbar.Snackbar
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
        val todoAdapter = TodoAdapter(requireContext(), viewModel)
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


        // this widget implement the deleting of to-do by swiping it right
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // get the item at a particular position
                val deletedTodo = viewModel.todos.value!![viewHolder.bindingAdapterPosition]

                // delete to-do from database
                viewModel.deleteTodo(deletedTodo)

                // update RecyclerView
                todoAdapter.notifyItemRemoved(viewHolder.bindingAdapterPosition)

                Snackbar.make(binding.rvTodoList, "The Todo was deleted", Snackbar.LENGTH_SHORT)
                    .setAction("Undo") {
                        viewModel.addNewTodo(deletedTodo)
                        todoAdapter.notifyItemInserted(deletedTodo.id)
                    }.show()
            }

        }).attachToRecyclerView(binding.rvTodoList)




        binding.svSearchTodo.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
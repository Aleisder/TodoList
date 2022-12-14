package com.example.todolist.screens.addnewtodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.todolist.R
import com.example.todolist.data.Todo
import com.example.todolist.databinding.FragmentAddNewTodoBinding
import com.example.todolist.screens.todolist.viewmodel.TodoListViewModel

class AddNewTodoFragment : Fragment() {

    private lateinit var binding: FragmentAddNewTodoBinding
    private val viewModel: TodoListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewTodoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btAddTodo.setOnClickListener {
            addNewTodo()
        }
    }

    private fun addNewTodo() {
        val todoTitle = binding.etNewTodoTitle.text.toString().trim()
        if (todoTitle != "") {
            viewModel.addNewTodo(Todo(0, todoTitle))
            makeToast("Todo is added to the list")
            Navigation.findNavController(requireView()).navigate(R.id.action_addNewTodoFragment_to_todoListFragment)
        } else {
            makeToast("The field can not be empty!")
        }

    }

    private fun makeToast(message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()


}
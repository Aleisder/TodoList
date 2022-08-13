package com.example.todolist.screens.addnewtodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.todolist.R
import com.example.todolist.databinding.FragmentAddNewTodoBinding
import com.example.todolist.viewmodel.TodoListViewModel


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
            if (binding.tilNewTodo.text.isNotEmpty()) {
                viewModel.addNewTodo(binding.tilNewTodo.text.toString())
                Toast.makeText(context,"Todo is added to the list", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).navigate(R.id.action_addNewTodoFragment_to_todoListFragment)
            } else {
                Toast.makeText(context,"The field can not be empty!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package com.example.todolist

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.todolist.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.apply {
            //remove the default shadow of view
            background = null

            //disable the item-placeholder in menu
            menu.getItem(1).isEnabled = false
        }


        val navController = findNavController(R.id.fragment_container)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        binding.bottomNavigationView.setupWithNavController(navController)

        binding.fabAddNewTodo.setOnClickListener {
            navController.navigate(R.id.action_todoListFragment_to_addNewTodoFragment)
            Toast.makeText(this, "Add new Todo", Toast.LENGTH_SHORT).show()
        }


    }
}
package com.example.todolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.Todo
import com.example.todolist.data.TodoRepository
import kotlinx.coroutines.launch

class TodoListViewModel (private val repository: TodoRepository) : ViewModel() {
    val todos = repository.selectAllTodos().asLiveData()

    fun insertTodo(todo: Todo) {
        viewModelScope.launch {
            repository.insertTodo(todo)
        }
    }
}
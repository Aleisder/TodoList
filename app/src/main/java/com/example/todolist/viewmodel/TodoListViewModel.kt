package com.example.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.data.Todo
import com.example.todolist.data.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoListViewModel (private val repository: TodoRepository) : ViewModel() {
    private val _todos = MutableLiveData<Flow<List<Todo>>>()
    val todos: LiveData<Flow<List<Todo>>> = _todos


    init {
        _todos.value = repository.selectAllTodos()
    }
}
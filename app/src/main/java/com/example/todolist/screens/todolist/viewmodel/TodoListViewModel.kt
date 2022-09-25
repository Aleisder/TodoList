package com.example.todolist.screens.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.Todo
import com.example.todolist.data.TodoRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepositoryImpl
    ) : ViewModel() {

    //holds all todos
    private val _todos = repository.selectAllTodos().asLiveData()
    val todos get() = _todos

    //add new to-do to the end of list
    fun addNewTodo(todo: Todo) {
        viewModelScope.launch {
            repository.insertTodo(todo)
        }
    }

    fun changeIsDone(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            val isDone = todo.isDone
            repository.updateTodo(Todo(todo.id, todo.title, !isDone ))
        }
    }

    fun searchTodos(query: String): LiveData<List<Todo>> {
        return repository.searchTodoByQuery(query).asLiveData()
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodo(todo)
        }
    }

}
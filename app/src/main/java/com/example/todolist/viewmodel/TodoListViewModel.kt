package com.example.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.Todo
import com.example.todolist.data.TodoDao
import com.example.todolist.data.TodoRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(private val repository: TodoRepositoryImpl) : ViewModel() {

    //holds all todos
    val todos = repository.selectAllTodos().asLiveData()

    //add new to-do to the end of list
    fun addNewTodo(title: String) {
        viewModelScope.launch {
            val todo = Todo(0, title)
            repository.insertTodo(todo)
        }
    }

    fun searchTodos(query: String): LiveData<List<Todo>> {
        return repository.searchTodoByQuery(query).asLiveData()
    }


    fun onTodoSelected(todo: Todo) {}
    fun onTodoCheckedChanged(todo: Todo, isChecked: Boolean) = viewModelScope.launch {
        repository.updateTodo(todo.copy(isDone = isChecked))
    }
}
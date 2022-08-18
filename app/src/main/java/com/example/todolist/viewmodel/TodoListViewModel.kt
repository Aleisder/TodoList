package com.example.todolist.viewmodel

import androidx.lifecycle.*
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
            val todo = Todo(title = title)
            repository.insertTodo(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
      //todo
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
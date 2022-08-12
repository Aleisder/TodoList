package com.example.todolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.todolist.data.TodoDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(private val dao: TodoDao) : ViewModel() {

    val todos = dao.selectAllTodos().asLiveData()

}
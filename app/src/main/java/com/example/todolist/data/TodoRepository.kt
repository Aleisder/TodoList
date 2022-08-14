package com.example.todolist.data

import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun selectAllTodos(): Flow<List<Todo>>

    suspend fun insertTodo(todo: Todo)

    suspend fun updateTodo(todo: Todo)

    fun searchTodoByQuery(searchQuery: String): Flow<List<Todo>>

}
package com.example.todolist.data

import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun selectAllTodos(): Flow<List<Todo>>

    fun selectTodoById(id: Int): Todo?

    suspend fun insertTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)

}
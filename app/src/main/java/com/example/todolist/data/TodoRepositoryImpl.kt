package com.example.todolist.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(private val dao: TodoDao) : TodoRepository  {

    override fun selectAllTodos(): Flow<List<Todo>> {
        return dao.selectAllTodos()
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo)
    }

}
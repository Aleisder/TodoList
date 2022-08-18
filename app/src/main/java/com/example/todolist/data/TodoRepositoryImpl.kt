package com.example.todolist.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(private val dao: TodoDao) : TodoRepository  {

    override fun selectAllTodos(): Flow<List<Todo>> {
        return dao.selectAllTodos()
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo)
    }

    override suspend fun updateTodo(todo: Todo) {
        dao.updateTodo(todo)
    }

    override fun searchTodoByQuery(searchQuery: String): Flow<List<Todo>> {
        return dao.searchTodoByQuery(searchQuery)
    }

    override fun getTodoById(id: Int): Flow<Todo> {
        return dao.getTodoById(id)
    }

    override fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo)
    }

}
package com.example.todolist.data

import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(private val dao: TodoDao) : TodoRepository  {

    override fun selectAllTodos(): Flow<List<Todo>> {
        return dao.selectAllTodos()
    }

    override fun selectTodoById(id: Int): Todo? {
        return dao.selectTodoById(id)
    }

    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo)
    }
}
package com.example.todolist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_table")
    fun selectAllTodos(): Flow<List<Todo>>

    @Query("SELECT * FROM todo_table WHERE id = :id")
    fun selectTodoById(id: Int): Todo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Delete()
    suspend fun deleteTodo(todo: Todo)

}
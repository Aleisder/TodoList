package com.example.todolist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_table")
    fun selectAllTodos(): Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodo(todo: Todo)

    //update data about a single to-do
    //didn't implement yet
    @Update
    suspend fun updateTodo(todo: Todo)

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery")
    fun searchTodoByQuery(searchQuery: String): Flow<List<Todo>>

}
package com.example.todolist.screens

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.Todo
import com.example.todolist.databinding.ListItemBinding

class TodoAdapter(val context: Context, val todoList: List<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val layoutAdapter = ListItemBinding.inflate(LayoutInflater.from(parent.context))
        return TodoViewHolder(layoutAdapter)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todoList[position]
            tvTodoTitle.text = todo.title
            cbIsDone.isChecked = false
        }
    }

    override fun getItemCount(): Int = todoList.size
}
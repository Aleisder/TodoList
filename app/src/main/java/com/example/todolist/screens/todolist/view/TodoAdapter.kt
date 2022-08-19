package com.example.todolist.screens.todolist.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.Todo
import com.example.todolist.databinding.ListItemBinding

class TodoAdapter(private val context: Context) : ListAdapter<Todo, TodoAdapter.TodoViewHolder>(
    DiffCallback()
) {

    class TodoViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val layout = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(layout)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = getItem(position)
        holder.binding.apply {
            tvTodoTitle.text = currentTodo.title
            cbIsDone.isChecked = currentTodo.isDone
            cbIsDone.setOnClickListener {
                Toast.makeText(context, "Checkbox clicked", Toast.LENGTH_SHORT).show()

            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Todo, newItem: Todo) = oldItem == newItem
    }

}
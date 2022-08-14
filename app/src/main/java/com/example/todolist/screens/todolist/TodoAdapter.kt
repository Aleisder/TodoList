package com.example.todolist.screens.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.Todo
import com.example.todolist.databinding.ListItemBinding

class TodoAdapter(private var listener: OnItemClickListener) : ListAdapter<Todo, TodoAdapter.TodoViewHolder>(DiffCallback()) {

    inner class TodoViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                root.setOnClickListener {
                    val pos = bindingAdapterPosition
                    if (pos != RecyclerView.NO_POSITION) {
                        val todo = getItem(pos)
                        listener.onItemClick(todo)
                    }
                }
                cbIsDone.setOnClickListener {
                    val pos = bindingAdapterPosition
                    if (pos != RecyclerView.NO_POSITION) {
                        val todo = getItem(pos)
                        listener.onCheckboxClick(todo, cbIsDone.isChecked)
                    }
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(todo: Todo)
        fun onCheckboxClick(todo: Todo, isChecked: Boolean)
    }

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
                if (cbIsDone.isChecked) {

                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Todo, newItem: Todo) = oldItem == newItem
    }

}
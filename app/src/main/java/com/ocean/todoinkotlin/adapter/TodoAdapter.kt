package com.ocean.todoinkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ocean.todoinkotlin.databinding.RvItemTodoBinding
import com.ocean.todoinkotlin.model.TodoModel

class TodoAdapter(val list: List<TodoModel>) : RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RvItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
//    : Int {
//        TODO("Not yet implemented")
//    }

    class MyViewHolder(val binding: RvItemTodoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TodoModel){
            binding.txtShowTitle.text = data.tittle
            binding.txtShowTask.text = data.description
            binding.txtShowCategory.text = data.category
            binding.txtShowDate.text = data.date.toString()
            binding.txtShowTime.text = data.time.toString()
        }
    }
}
package com.example.networkex.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.networkex.databinding.ItemBinding
import com.example.networkex.model.UserVO

class MainAdapter : ListAdapter<UserVO, MainAdapter.MainViewHolder>(DIFF_UTIL) {
    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<UserVO>() {
            override fun areItemsTheSame(oldItem: UserVO, newItem: UserVO): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserVO, newItem: UserVO): Boolean {
                return oldItem == newItem
            }
        }
    }

    class MainViewHolder(private val binding: ItemBinding) : ViewHolder(binding.root) {
        fun bind(item: UserVO, position: Int) {
            binding.item = item.copy(login = "$position - ${item.login}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder((binding))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}
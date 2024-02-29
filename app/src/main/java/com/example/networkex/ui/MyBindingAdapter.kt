package com.example.networkex.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.networkex.model.UserVO


@BindingAdapter("setAdapter")
fun RecyclerView.setAdapter(userList: List<UserVO>) {
    if (this.adapter == null) {
        this.adapter = MainAdapter()
        addItemDecoration(DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL))
    }

    (this.adapter as MainAdapter).submitList(userList)
}


package com.wimi.idolmaster.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.wimi.idolmaster.BR

class BaseViewHolder<T>(private val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }

    fun getBinding() = binding
}
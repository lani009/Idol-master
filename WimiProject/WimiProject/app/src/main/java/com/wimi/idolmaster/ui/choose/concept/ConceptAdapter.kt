package com.wimi.idolmaster.ui.choose.concept

import androidx.recyclerview.widget.DiffUtil
import com.wimi.idolmaster.R
import com.wimi.idolmaster.databinding.ItemConceptBinding
import com.wimi.idolmaster.ui.base.BaseRecyclerAdapter
import com.wimi.idolmaster.ui.base.BaseViewHolder

class ConceptAdapter : BaseRecyclerAdapter<String>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int) = R.layout.item_concept

    override fun onBindViewHolder(holder: BaseViewHolder<String>, position: Int) {
        super.onBindViewHolder(holder, position)

        val binding = holder.getBinding() as ItemConceptBinding
        val item = getItem(position)

        binding.conceptText.text = item
    }
}
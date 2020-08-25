package com.wimi.idolmaster.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.wimi.idolmaster.R
import com.wimi.idolmaster.domain.model.GistsPublic
import com.wimi.idolmaster.ui.base.BaseRecyclerAdapter

class GistsAdapter : BaseRecyclerAdapter<GistsPublic>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<GistsPublic>() {
        override fun areItemsTheSame(oldItem: GistsPublic, newItem: GistsPublic): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: GistsPublic, newItem: GistsPublic): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int) = R.layout.item_gists

}
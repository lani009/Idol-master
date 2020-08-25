package com.wimi.idolmaster.ui.choose.idol

import androidx.recyclerview.widget.DiffUtil
import com.wimi.idolmaster.R
import com.wimi.idolmaster.databinding.ItemConceptBinding
import com.wimi.idolmaster.databinding.ItemEntertainerBinding
import com.wimi.idolmaster.domain.model.IdolData
import com.wimi.idolmaster.ui.base.BaseRecyclerAdapter
import com.wimi.idolmaster.ui.base.BaseViewHolder

class IdolAdapter : BaseRecyclerAdapter<IdolData>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<IdolData>() {
        override fun areItemsTheSame(oldItem: IdolData, newItem: IdolData): Boolean {
            return oldItem.idolName == newItem.idolName
        }

        override fun areContentsTheSame(oldItem: IdolData, newItem: IdolData): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int) = R.layout.item_entertainer

    override fun onBindViewHolder(holder: BaseViewHolder<IdolData>, position: Int) {
        super.onBindViewHolder(holder, position)

        val binding = holder.getBinding() as ItemEntertainerBinding
        val item = getItem(position)

        binding.idolImage.setImageResource(item.idolImage)
    }

    /*  var idolDataArrayList = ArrayList<IdolData>()

      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdolViewHolder {
          return IdolViewHolder(
              LayoutInflater.from(parent.context)
                  .inflate(R.layout.item_entertainer, parent, false)
          )
      }

      override fun onBindViewHolder(holder: IdolViewHolder, position: Int) {
          val imageView = holder.itemView.findViewById<ImageView>(R.id.idolImage)
          imageView.setImageResource(idolDataArrayList[position].idolImage)
          val textView = holder.itemView.findViewById<TextView>(R.id.idolName)
          textView.text = idolDataArrayList[position].idolName

          holder.itemView.setOnClickListener { //textView.setTextColor(Color.parseColor("#6CFF72"));
              for ((index, idolData) in idolDataArrayList.withIndex()) {
                  idolData.isSelected.set(index == position)
              }
          }
      }

      override fun getItemCount(): Int {
          return idolDataArrayList.size
      }

      inner class IdolViewHolder(itemView: View) :
          RecyclerView.ViewHolder(itemView)*/
}
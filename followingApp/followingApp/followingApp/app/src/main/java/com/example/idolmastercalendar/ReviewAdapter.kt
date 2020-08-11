package com.example.idolmastercalendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.idolmastercalendar.data.Review
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter(val fragmentManager: FragmentManager): RecyclerView.Adapter<ReviewAdapter.ReviewHolder>() {

    val reviewList = ObservableArrayList<Review>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        return ReviewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false))
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        holder.bind(reviewList[position])
    }

    inner class ReviewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(review: Review) {
            itemView.textView8.text = review.content
            /*itemView.setOnClickListener {
                ReviewDialog.getInstance().show(fragmentManager, "reviewDialog")
            }*/
        }
    }
}
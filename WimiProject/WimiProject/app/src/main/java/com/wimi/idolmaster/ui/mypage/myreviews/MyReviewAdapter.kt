package com.wimi.idolmaster.ui.mypage.myreviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.wimi.idolmaster.R
import com.wimi.idolmaster.domain.model.MyReview
import kotlinx.android.synthetic.main.item_my_review.view.*
import kotlinx.android.synthetic.main.item_review.view.textView8

class MyReviewAdapter(): RecyclerView.Adapter<MyReviewAdapter.ReviewHolder>() {

    val reviewList = ObservableArrayList<MyReview>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        return ReviewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_my_review, parent, false))
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        holder.bind(reviewList[position])
    }

    inner class ReviewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(review: MyReview) {
            itemView.textView37.text = review.place
            itemView.textView8.text = review.content
        }
    }
}
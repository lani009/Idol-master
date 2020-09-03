package com.wimi.idolmaster.ui.mypage.myreviews

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wimi.idolmaster.R
import com.wimi.idolmaster.databinding.ActivityMyReviewsBinding
import com.wimi.idolmaster.ui.base.BaseActivity
import com.wimi.idolmaster.ui.review.ReviewAdapter
import kotlinx.android.synthetic.main.activity_my_reviews.*


class MyReviewsActivity : BaseActivity<ActivityMyReviewsBinding, MyReviewsViewModel>(
    R.layout.activity_my_reviews,
    MyReviewsViewModel::class
) {

    override fun onCreate() {
        initBackButton()
        initReviewRecycler()
        subscribeReviewList()
    }

    private fun initBackButton() {
        appBarLayout.setOnClickListener { finish() }
    }

    private fun initReviewRecycler() {
        myReviewRecycler.adapter = MyReviewAdapter()
        myReviewRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun subscribeReviewList() {
        viewModel.myReviewList.observe(this, Observer {
            (myReviewRecycler.adapter as MyReviewAdapter).reviewList.clear()
            (myReviewRecycler.adapter as MyReviewAdapter).reviewList.addAll(it)
            (myReviewRecycler.adapter as MyReviewAdapter).notifyDataSetChanged()
        })
    }
}
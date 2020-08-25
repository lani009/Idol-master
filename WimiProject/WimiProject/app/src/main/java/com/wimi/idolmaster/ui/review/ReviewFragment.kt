package com.wimi.idolmaster.ui.review

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wimi.idolmaster.R
import com.wimi.idolmaster.databinding.FragmentReviewBinding
import com.wimi.idolmaster.ui.base.BaseFragment
import com.wimi.idolmaster.ui.review.dialog.WriteReviewDialog
import com.wimi.idolmaster.utils.EventObserver
import kotlinx.android.synthetic.main.fragment_review.*

class ReviewFragment: BaseFragment<FragmentReviewBinding, ReviewViewModel>(
    R.layout.fragment_review,
    ReviewViewModel::class
) {

    override fun onCreate() {
        initReviewRecycler()
        subscribeReviewList()
        subscribeAddEvent()
        subscribeAddReviewEvent()
    }

    private fun initReviewRecycler() {
        reviewRecycler.adapter = ReviewAdapter(childFragmentManager)
        reviewRecycler.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    private fun subscribeReviewList() {
        viewModel.reviewList.observe(this, Observer {
            (reviewRecycler.adapter as ReviewAdapter).reviewList.clear()
            (reviewRecycler.adapter as ReviewAdapter).reviewList.addAll(it)
            (reviewRecycler.adapter as ReviewAdapter).notifyDataSetChanged()
        })
    }

    private fun subscribeAddEvent() {
        floatingActionButton5.setOnClickListener {
            WriteReviewDialog().show(childFragmentManager, "WriteReview")
        }
    }

    private fun subscribeAddReviewEvent() {
        viewModel.addReviewEvent.observe(this, EventObserver {
            WriteReviewDialog().show(childFragmentManager, "ReviewDialog")
        })
    }
}
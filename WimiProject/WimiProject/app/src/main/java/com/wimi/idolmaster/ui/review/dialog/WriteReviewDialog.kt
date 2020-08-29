package com.wimi.idolmaster.ui.review.dialog

import com.wimi.idolmaster.R
import com.wimi.idolmaster.databinding.DialogWriteReviewBinding
import com.wimi.idolmaster.ui.base.BaseDialogFragment
import com.wimi.idolmaster.ui.review.ReviewFragment
import com.wimi.idolmaster.utils.EventObserver


class WriteReviewDialog: BaseDialogFragment<DialogWriteReviewBinding, WriteReviewViewModel>(
    R.layout.dialog_write_review,
    WriteReviewViewModel::class
) {

    override fun onCreate() {
        subscribeFinishEvent()
    }

    private fun subscribeFinishEvent() {
        viewModel.finishEvent.observe(this, EventObserver {
            (parentFragment as ReviewFragment).viewModel.getReview()
            dismiss()
        })
    }

}
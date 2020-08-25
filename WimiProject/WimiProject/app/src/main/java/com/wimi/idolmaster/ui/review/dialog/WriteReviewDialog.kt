package com.wimi.idolmaster.ui.review.dialog

import com.wimi.idolmaster.R
import com.wimi.idolmaster.databinding.DialogWriteReviewBinding
import com.wimi.idolmaster.ui.base.BaseDialogFragment


class WriteReviewDialog: BaseDialogFragment<DialogWriteReviewBinding, WriteReviewViewModel>(
    R.layout.dialog_write_review,
    WriteReviewViewModel::class
) {

    override fun onCreate() {

    }

}
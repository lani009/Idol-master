package com.wimi.idolmaster.ui.mypage

import android.content.Intent
import com.wimi.idolmaster.MyApplication
import com.wimi.idolmaster.R
import com.wimi.idolmaster.databinding.FragmentMypageBinding
import com.wimi.idolmaster.ui.base.BaseFragment
import com.wimi.idolmaster.ui.choose.ChooserActivity
import com.wimi.idolmaster.ui.mypage.myreviews.MyReviewsActivity
import com.wimi.idolmaster.utils.EventObserver
import kotlinx.android.synthetic.main.fragment_mypage.*

/**
 * @author ReStartAllKill
 * @created on 2019-05-28
 * @modified by
 * @updated on
 */

class MyPageFragment : BaseFragment<FragmentMypageBinding, MyPageViewModel>(
    R.layout.fragment_mypage,
    MyPageViewModel::class
) {

    override fun onCreate() {
        initMyIdol()
        subscribeMyReviewEvent()
        subscribeEditEvent()
        subscribeLogoutEvent()
    }

    private fun initMyIdol() {
        imageView4.setImageResource(viewModel.getApplication<MyApplication>().myIdol.value!!.idolImage)
    }

    private fun subscribeMyReviewEvent() {
        myReview.setOnClickListener {
            startActivity(Intent(requireActivity(), MyReviewsActivity::class.java))
        }
    }

    private fun subscribeEditEvent() {
        viewModel.editEvent.observe(this, EventObserver {
            startActivity(Intent(requireActivity(), ChooserActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            })
        })
    }

    private fun subscribeLogoutEvent() {
        viewModel.logoutEvent.observe(this, EventObserver {
            requireActivity().finish()
        })
    }
}
package com.wimi.idolmaster.ui.home

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wimi.idolmaster.R
import com.wimi.idolmaster.databinding.FragmentHomeBinding
import com.wimi.idolmaster.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @author ReStartAllKill
 * @created on 2019-05-28
 * @modified by
 * @updated on
 */

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    R.layout.fragment_home,
    HomeViewModel::class
) {

    private val gistsAdapter = GistsAdapter()

    override fun onCreate() {
        initView()
        initGistsObserve()
    }

    private fun initView() {
    }

    private fun initGistsObserve() {
        viewModel.gistsPublicDataList.observe(this, Observer {
            it.let(gistsAdapter::submitList)
        })
    }

}
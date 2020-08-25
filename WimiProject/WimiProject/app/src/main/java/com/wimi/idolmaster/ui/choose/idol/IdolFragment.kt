package com.wimi.idolmaster.ui.choose.idol

import androidx.recyclerview.widget.GridLayoutManager
import com.wimi.idolmaster.MyApplication
import com.wimi.idolmaster.R
import com.wimi.idolmaster.databinding.FragmentIdolBinding
import com.wimi.idolmaster.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_idol.*

class IdolFragment: BaseFragment<FragmentIdolBinding, IdolViewModel>(
    R.layout.fragment_idol,
    IdolViewModel::class
) {

    override fun onCreate() {
        initIdolRecycler()
    }

    private fun initIdolRecycler() {
        recyclerview.adapter = IdolAdapter().apply {
            submitList(viewModel.idolList)

            setOnItemClickListener { _, idol, position ->
                for((index, item) in viewModel.idolList.withIndex())
                    item.isSelected.set(index == position)

                viewModel.getApplication<MyApplication>().myIdol.value = idol
            }
        }

        recyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
    }

}
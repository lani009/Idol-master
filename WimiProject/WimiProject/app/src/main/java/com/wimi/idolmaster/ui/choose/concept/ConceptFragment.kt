package com.wimi.idolmaster.ui.choose.concept

import androidx.recyclerview.widget.GridLayoutManager
import com.wimi.idolmaster.R
import com.wimi.idolmaster.databinding.FragmentConceptBinding
import com.wimi.idolmaster.ui.base.BaseFragment
import com.wimi.idolmaster.utils.EventObserver
import kotlinx.android.synthetic.main.fragment_concept.*

class ConceptFragment: BaseFragment<FragmentConceptBinding, ConceptViewModel>(
    R.layout.fragment_concept,
    ConceptViewModel::class
) {

    override fun onCreate() {
        initConceptRecycler()
        subscribeTextListChangeEvent()
    }

    private fun initConceptRecycler() {
        textRecycler.adapter = ConceptAdapter()
        textRecycler.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    private fun subscribeTextListChangeEvent() {
        viewModel.textChangeEvent.observe(this, EventObserver {
            (textRecycler.adapter as ConceptAdapter).submitList(viewModel.textList.toMutableList())
        })
    }
}
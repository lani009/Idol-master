package com.wimi.idolmaster.ui.choose.idol

import android.app.Application
import com.wimi.idolmaster.R
import com.wimi.idolmaster.domain.model.IdolData
import com.wimi.idolmaster.ui.base.BaseViewModel

class IdolViewModel(application: Application): BaseViewModel(application) {

    val idolList = ArrayList<IdolData>().apply {
        add(IdolData("BTS", R.drawable.bts_image))
        add(IdolData("RED\nVELVET", R.drawable.redvelvet_image))
        add(IdolData("CHUNG\nHA", R.drawable.chungha_image))
        add(IdolData("BLACK\nPINK", R.drawable.blackpink_image))
        add(IdolData("OH\nMY\nGIRL", R.drawable.ohmygirl_image))
        add(IdolData("DAY6", R.drawable.day6_image))
    }
}
package com.wimi.idolmaster.domain.model

import androidx.annotation.DrawableRes
import androidx.databinding.ObservableField

data class IdolData(
    var idolName: String,
    @DrawableRes var idolImage: Int
) {
    val isSelected = ObservableField(false)
}
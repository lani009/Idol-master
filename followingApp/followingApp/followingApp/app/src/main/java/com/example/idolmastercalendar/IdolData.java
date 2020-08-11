package com.example.idolmastercalendar;

import androidx.annotation.DrawableRes;

public class IdolData {

    String idolName;
    @DrawableRes int idolImage;

    public IdolData(String idolName, @DrawableRes int idolImage) {
        this.idolName = idolName;
        this.idolImage = idolImage;
    }

    public String getIdolName() {
        return idolName;
    }

    public int getIdolImage() {
        return idolImage;
    }
}

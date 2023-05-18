package org.d3if6706213098GaleriHewan.model

import org.d3if6706213098GaleriHewan.db.BmrEntity

fun BmrEntity.hitungBmr(): HasilBmr {
    val bmr : Float
    if (isMale) {
        bmr = (66.5 + (13.7*berat.toFloat()) + (5*tinggi.toFloat()) - (6.8*umur.toFloat())).toFloat()
    } else {
        bmr = (65.5 + (9.6*berat.toFloat()) + (1.8*tinggi.toFloat()) - (4.7*umur.toFloat())).toFloat()
    }
    return HasilBmr(bmr)
}
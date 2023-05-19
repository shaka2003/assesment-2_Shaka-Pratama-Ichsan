package org.d3if6706213098GaleriHewan.model

import org.d3if6706213098GaleriHewan.db.BmiEntity

fun BmiEntity.hitungBmi(): HasilBmi {
    val bmi : Float
    if (isMale) {
        bmi = (66.5 + (13.7*berat) + (5*tinggi) - (6.8*umur)).toFloat()
    } else {
        bmi = (65.5 + (9.6*berat) + (1.8*tinggi) - (4.7*umur)).toFloat()
    }


    val sangatJarang = bmi * 1.2F
    val jarang = bmi * 1.375F
    val normal = bmi * 1.55F
    val sering = bmi * 1.725F
    val sangatSering = bmi * 1.9F

    val kategori = if (isMale) {
        when {
            bmi < 20.5 -> KategoriBmi.KURUS
            bmi >= 27.0 -> KategoriBmi.GEMUK
            else -> KategoriBmi.IDEAL
        }
    } else {
        when {
            bmi < 18.5 -> KategoriBmi.KURUS
            bmi >= 25.0 -> KategoriBmi.GEMUK
            else -> KategoriBmi.IDEAL
        }
    }
    return HasilBmi(bmi, kategori, sangatJarang, jarang, normal, sering, sangatSering)
}
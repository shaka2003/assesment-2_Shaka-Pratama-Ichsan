package org.d3if6706213098GaleriHewan.model

data class HasilBmi(
    val bmi: Float,
//    val bmr: Float,
    val kategori: KategoriBmi,
    val sangatJarang: Float,
    val jarang: Float,
    val normal: Float,
    val sering: Float,
    val sangatSering: Float,
)

package org.d3if6706213098GaleriHewan.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if6706213098GaleriHewan.db.BmiDao
import org.d3if6706213098GaleriHewan.db.BmiEntity
import org.d3if6706213098GaleriHewan.db.BmrEntity
import org.d3if6706213098GaleriHewan.model.HasilBmi
import org.d3if6706213098GaleriHewan.model.KategoriBmi
import org.d3if6706213098GaleriHewan.model.HasilBmr
import org.d3if6706213098GaleriHewan.model.hitungBmi
import org.d3if6706213098GaleriHewan.model.hitungBmr

class HitungViewModel(private val db: BmiDao) : ViewModel() {
    private val hasilBmi = MutableLiveData<HasilBmi?>()
    private val hasilBmr = MutableLiveData<HasilBmr?>()
    private val navigasi = MutableLiveData<KategoriBmi?>()

    fun hitungBmi(berat: Float, tinggi: Float, isMale: Boolean)  {
        val dataBmi = BmiEntity(
            berat = berat,
            tinggi = tinggi,
            isMale = isMale,
        )
        hasilBmi.value = dataBmi.hitungBmi()


        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataBmi)
            }
        }
    }

//    fun hitungBmr(berat: Float, tinggi: Float, umur: Float, isMale: Boolean)  {
//        val dataBmr = BmrEntity(
//            berat = berat,
//            tinggi = tinggi,
//            umur = umur,
//            isMale = isMale,
//        )
//        hasilBmr.value = dataBmr.hitungBmr()
//
//
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                db.insert(dataBmr)
//            }
//        }
//    }

    fun getHasilBmi(): LiveData<HasilBmi?> = hasilBmi

    fun mulaiNavigasi() {
        navigasi.value = hasilBmi.value?.kategori
    }

    fun selesaiNavigasi() {
        navigasi.value = null
    }

    fun getNavigasi() : LiveData<KategoriBmi?> = navigasi
}
package org.d3if6706213098GaleriHewan.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if6706213098GaleriHewan.db.BmrDao
import org.d3if6706213098GaleriHewan.db.BmrEntity
import org.d3if6706213098GaleriHewan.model.HasilBmr
import org.d3if6706213098GaleriHewan.model.hitungBmr

class HitungBmrViewModel(private val db: BmrDao) : ViewModel() {
    private val hasilBmr = MutableLiveData<HasilBmr?>()

    fun hitungBmr(berat: Float, tinggi: Float, umur: Float, isMale: Boolean)  {
        val dataBmr = BmrEntity(
            berat = berat,
            tinggi = tinggi,
            umur = umur,
            isMale = isMale,
        )
        hasilBmr.value = dataBmr.hitungBmr()


        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataBmr)
            }
        }
    }

    fun getHasilBmr(): LiveData<HasilBmr?> = hasilBmr
}
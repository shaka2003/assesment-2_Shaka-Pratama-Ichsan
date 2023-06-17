package org.d3if6706213098GaleriHewan.ui.hitung

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if6706213098GaleriHewan.db.BmiDao
import org.d3if6706213098GaleriHewan.db.BmiEntity
import org.d3if6706213098GaleriHewan.model.HasilBmi
import org.d3if6706213098GaleriHewan.model.KategoriBmi
import org.d3if6706213098GaleriHewan.model.Makanan
import org.d3if6706213098GaleriHewan.model.hitungBmi
import org.d3if6706213098GaleriHewan.network.ApiStatus
import org.d3if6706213098GaleriHewan.network.MakananApi

class HitungViewModel(private val db: BmiDao) : ViewModel() {
    private val hasilBmi = MutableLiveData<HasilBmi?>()
    private val navigasi = MutableLiveData<KategoriBmi?>()
    private val data = MutableLiveData<List<Makanan>>()
    private val status = MutableLiveData<ApiStatus>()


    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
//                val result = MakananApi.service.getMakanan()
//                Log.d("MakananViewModel", "Success: $result")
                data.postValue(MakananApi.service.getMakanan())
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("MakananViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun hitungBmi(berat: Float, tinggi: Float, isMale: Boolean, umur: Float)  {
        val dataBmi = BmiEntity(
            berat = berat,
            tinggi = tinggi,
            isMale = isMale,
            umur = umur
        )
        hasilBmi.value = dataBmi.hitungBmi()


        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataBmi)
            }
        }
    }

    fun getHasilBmi(): LiveData<HasilBmi?> = hasilBmi

    fun mulaiNavigasi() {
        navigasi.value = hasilBmi.value?.kategori
    }

    fun selesaiNavigasi() {
        navigasi.value = null
    }

    fun getNavigasi() : LiveData<KategoriBmi?> = navigasi

    fun getData(): LiveData<List<Makanan>> = data
    fun getStatus(): LiveData<ApiStatus> = status
}
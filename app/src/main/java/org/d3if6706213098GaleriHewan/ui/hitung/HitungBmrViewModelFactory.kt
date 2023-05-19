package org.d3if6706213098GaleriHewan.ui.hitung

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if6706213098GaleriHewan.db.BmiDao
import org.d3if6706213098GaleriHewan.db.BmrDao

class HitungBmrViewModelFactory (
    private val db: BmrDao
    ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HitungViewModel::class.java)) {
                return HitungBmrViewModel(db) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
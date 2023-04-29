package org.d3if6706213098GaleriHewan.ui.histori

import androidx.lifecycle.ViewModel
import org.d3if6706213098GaleriHewan.db.BmiDao

class HistoriViewModel(db: BmiDao) : ViewModel() {
    val data = db.getLastBmi()
}
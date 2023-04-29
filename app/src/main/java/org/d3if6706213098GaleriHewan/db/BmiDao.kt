package org.d3if6706213098GaleriHewan.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BmiDao {
    @Insert
    fun insert(bmi: BmiEntity)
    @Query("SELECT * FROM bmi ORDER BY id DESC LIMIT 1")
    fun getLastBmi(): LiveData<BmiEntity?>
}
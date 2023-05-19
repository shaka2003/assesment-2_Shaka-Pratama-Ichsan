package org.d3if6706213098GaleriHewan.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BmrDao {
    @Insert
    fun insert(bmr: BmrEntity)
    @Query("SELECT * FROM bmi ORDER BY id DESC")
    fun getLastBmi(): LiveData<List<BmrEntity>>

    @Query("DELETE FROM bmi")
    fun getAll(): List<BmrEntity>
}
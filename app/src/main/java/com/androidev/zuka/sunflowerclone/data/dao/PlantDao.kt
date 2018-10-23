package com.androidev.zuka.sunflowerclone.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androidev.zuka.sunflowerclone.data.model.Plant

@Dao
interface PlantDao {
    // return all plants from database
    @Query("SELECT * FROM plants ORDER BY name")
    fun getPlants() : LiveData<List<Plant>>

    @Query("SELECT * FROM plants WHERE growZoneNumber = :growZoneNumber ORDER BY name")
    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int) : LiveData<List<Plant>>

    @Query ("SELECT * FROM plants WHERE id = :plantId")
    fun getPlant(plantId: String) : List<Plant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(plants: List<Plant>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlant(plant : Plant) : Long
}
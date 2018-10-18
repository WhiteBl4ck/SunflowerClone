package com.androidev.zuka.sunflowerclone.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.androidev.zuka.sunflowerclone.data.model.GardenPlanting
import com.androidev.zuka.sunflowerclone.data.model.PlantAndGardenPlanting

@Dao
interface GardenPlantingDao {
    @Query("SELECT * FROM garden_plantings")
    fun getGardenPlanting() : LiveData<List<GardenPlanting>>

    @Query("SELECT * FROM garden_plantings WHERE id = :gardenPlantId")
    fun getGardenPlanting(gardenPlantId : Long) : LiveData<GardenPlanting>

    @Query("SELECT * FROM garden_plantings WHERE plant_id = :plantId")
    fun getGardenPlantingForPlant(plantId:String) : LiveData<GardenPlanting>

    @Transaction
    @Query("SELECT * FROM plants")
    fun getPlantAndGardenPlantings() : LiveData<List<PlantAndGardenPlanting>>

    @Insert
    fun insertGardenPlanting(gardenPlanting: GardenPlanting) : Long

    @Delete
    fun deleteGardenPlanting(gardenPlanting: GardenPlanting)
}
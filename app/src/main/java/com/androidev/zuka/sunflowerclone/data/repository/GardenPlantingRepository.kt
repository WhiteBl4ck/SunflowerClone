package com.androidev.zuka.sunflowerclone.data.repository

import androidx.lifecycle.LiveData
import com.androidev.zuka.sunflowerclone.data.dao.GardenPlantingDao
import com.androidev.zuka.sunflowerclone.data.model.GardenPlanting
import com.androidev.zuka.sunflowerclone.data.model.PlantAndGardenPlanting

class GardenPlantingRepository private constructor(
        private val gardenPlantingDao: GardenPlantingDao
) {

    fun getGardenPlanting(gardenPlantId: Long): LiveData<GardenPlanting> =
            gardenPlantingDao.getGardenPlanting(gardenPlantId)

    fun getGardenPlantings(): LiveData<List<GardenPlanting>> =
            gardenPlantingDao.getGardenPlantings()

    fun getGardenPlantingForPlant(plantId: String): LiveData<GardenPlanting> =
            gardenPlantingDao.getGardenPlantingForPlant(plantId)

    fun getPlantAndGardenPlantings(): LiveData<List<PlantAndGardenPlanting>> =
            gardenPlantingDao.getPlantAndGardenPlantings()

    fun insertGardenPlantings(gardenPlanting: GardenPlanting): Long =
            gardenPlantingDao.insertGardenPlanting(gardenPlanting)

    fun deleteGardenPlantings(gardenPlanting: GardenPlanting) =
            gardenPlantingDao.deleteGardenPlanting(gardenPlanting)

    companion object {
        @Volatile
        private var instance: GardenPlantingRepository? = null

        fun getInstance(gardenPlantingDao: GardenPlantingDao) =
                instance ?: synchronized(this)
                {
                    instance ?: GardenPlantingRepository(gardenPlantingDao).also { instance = it }
                }
    }
}
package com.androidev.zuka.sunflowerclone.data.repository

import com.androidev.zuka.sunflowerclone.data.dao.PlantDao
import com.androidev.zuka.sunflowerclone.data.model.Plant

/**
 * Repository module for handling data operations.
 */

class PlantRepository private constructor(private val plantDao: PlantDao) {
    fun getPlants() = plantDao.getPlants()

    fun getPlant(plantId: String) = plantDao.getPlant(plantId)

    fun getPlantsWithGrowZoneNumber(growZoneNumber : Int) =
            plantDao.getPlantsWithGrowZoneNumber(growZoneNumber)

    fun insertPlant(plant: Plant) : String = plantDao.insertPlant(plant)


    companion object {
        // Singleton
        @Volatile private var insance : PlantRepository? = null
        fun getInstance(plantDao: PlantDao) =
                insance ?: synchronized(this){
                    insance ?: PlantRepository(plantDao).also { insance = it }
                }
    }

}
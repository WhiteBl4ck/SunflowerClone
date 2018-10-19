package com.androidev.zuka.sunflowerclone.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.androidev.zuka.sunflowerclone.data.dao.GardenPlantingDao
import com.androidev.zuka.sunflowerclone.data.dao.PlantDao
import com.androidev.zuka.sunflowerclone.data.model.GardenPlanting
import com.androidev.zuka.sunflowerclone.data.model.Plant

@Database(entities = [Plant::class,GardenPlanting::class],version = 1,exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun plantDao() : PlantDao
    abstract fun gardenPlantingDao() : GardenPlantingDao

    companion object {
        @Volatile var instance : AppDatabase? = null

        fun getInstance(context : Context) =
                instance ?: synchronized(this)
                {
                    instance ?:
                }
    }
}
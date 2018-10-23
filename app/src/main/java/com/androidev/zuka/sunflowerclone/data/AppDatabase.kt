package com.androidev.zuka.sunflowerclone.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.androidev.zuka.sunflowerclone.data.dao.GardenPlantingDao
import com.androidev.zuka.sunflowerclone.data.dao.PlantDao
import com.androidev.zuka.sunflowerclone.data.model.GardenPlanting
import com.androidev.zuka.sunflowerclone.data.model.Plant
import com.androidev.zuka.sunflowerclone.utilities.DATABASE_NAME
import com.androidev.zuka.sunflowerclone.workers.SeedDatabaseWorker

@Database(entities = [Plant::class, GardenPlanting::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao
    abstract fun gardenPlantingDao(): GardenPlantingDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context) =
                instance ?: synchronized(this)
                {
                    instance ?: buildDatabase(context).also { instance = it }
                }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                            WorkManager.getInstance().enqueue(request)
                        }
                    })
                    .build()
        }
    }
}
package com.androidev.zuka.sunflowerclone.workers

import android.content.Context

import androidx.work.Worker
import androidx.work.WorkerParameters
import com.androidev.zuka.sunflowerclone.data.AppDatabase
import com.androidev.zuka.sunflowerclone.data.model.Plant
import com.androidev.zuka.sunflowerclone.utilities.PLANT_DATA_FILENAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader

class SeedDatabaseWorker(context: Context,workerParameters: WorkerParameters) : Worker(context,workerParameters) {
    private val TAG by lazy {  SeedDatabaseWorker::class.java.simpleName}


    override fun doWork(): Result {

        var jsonReader : JsonReader? = null
        val plantType = object : TypeToken<List<Plant>>() {}.type

        return try {
            val inputStream = applicationContext.assets.open(PLANT_DATA_FILENAME)
            jsonReader = JsonReader(inputStream.reader())
            val plantList : List<Plant> = Gson().fromJson(jsonReader,plantType)
            AppDatabase.getInstance(applicationContext).plantDao().insertAll(plantList)
            Result.SUCCESS

        } catch (ex : Exception)
        {
            Result.FAILURE
        } finally {
            jsonReader?.close()
        }
    }

}
package com.androidev.zuka.sunflowerclone.utilities

import android.content.Context
import com.androidev.zuka.sunflowerclone.data.AppDatabase
import com.androidev.zuka.sunflowerclone.data.dao.PlantDao
import com.androidev.zuka.sunflowerclone.data.repository.PlantRepository
import com.androidev.zuka.sunflowerclone.ui.viewmodel.viewModelFactory.PlantListViewModelFactory

object InjectorUtils {
    private fun getPlantRepository(context: Context) =
            PlantRepository.getInstance(AppDatabase.getInstance(context).plantDao())
    fun providePlantListViewModelFactory(context: Context): PlantListViewModelFactory {
        val repository = getPlantRepository(context)
        return PlantListViewModelFactory(repository)
    }
}
package com.androidev.zuka.sunflowerclone.ui.viewmodel

import androidx.arch.core.util.Function
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.androidev.zuka.sunflowerclone.data.model.Plant
import com.androidev.zuka.sunflowerclone.data.repository.PlantRepository

class PlantListViewModel internal constructor(private val plantListRepository: PlantRepository) : ViewModel()
{
    private val growZoneNumber = MutableLiveData<Int>();
    private val plantList = MediatorLiveData<List<Plant>>()

    init {
        growZoneNumber.value = NO_GROW_ZONE

        val livePlantList = Transformations.switchMap(growZoneNumber) { growzoneNumber ->
            if (growzoneNumber == NO_GROW_ZONE) {
                plantListRepository.getPlants()
            } else {
                plantListRepository.getPlantsWithGrowZoneNumber(growzoneNumber)
            }
        }
        plantList.addSource(livePlantList,plantList::setValue)
    }
    fun getPlants() = plantList
    fun setGrowZoneNumber(num: Int) {
        growZoneNumber.value = num
    }
    fun clearZoneNumber() {
        growZoneNumber.value = NO_GROW_ZONE
    }
    fun isFiltred() = growZoneNumber.value != NO_GROW_ZONE

    companion object {
        private const val NO_GROW_ZONE = -1
    }
}
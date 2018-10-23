package com.androidev.zuka.sunflowerclone.ui.viewmodel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidev.zuka.sunflowerclone.data.repository.PlantRepository
import com.androidev.zuka.sunflowerclone.ui.viewmodel.PlantListViewModel

class PlantListViewModelFactory(private val repository: PlantRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlantListViewModel(repository) as T
    }
}
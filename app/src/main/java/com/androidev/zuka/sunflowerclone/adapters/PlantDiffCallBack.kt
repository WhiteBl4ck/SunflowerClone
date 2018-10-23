package com.androidev.zuka.sunflowerclone.adapters

import androidx.recyclerview.widget.DiffUtil
import com.androidev.zuka.sunflowerclone.data.model.Plant

class PlantDiffCallBack : DiffUtil.ItemCallback<Plant>(){
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.plantId == newItem.plantId
    }
    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }

}

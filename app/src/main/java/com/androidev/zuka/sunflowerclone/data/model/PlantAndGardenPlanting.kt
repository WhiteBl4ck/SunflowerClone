package com.androidev.zuka.sunflowerclone.data.model

import androidx.room.Embedded
import androidx.room.Relation

class PlantAndGardenPlanting {
    @Embedded
    var Plant : Plant? = null;

    @Relation(parentColumn = "id", entityColumn = "plant_id")
    var gardenPlantings : List<GardenPlanting> = arrayListOf()
}
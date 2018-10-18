package com.androidev.zuka.sunflowerclone.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import java.util.Calendar.DAY_OF_YEAR


@Entity(tableName = "plants")
data class Plant (
        @PrimaryKey @ColumnInfo(name = "id") val  plantId : String,
        val  name : String,
        val description : String,
        val growZoneNumber : Int,
        val wateringInterval : Int = 7,     // how often the plant should be watered in days
        val imageUrl : String = ""
){
    /*
    *  Determine if the plant should be watered, returns true if since date of last watering + watering interval ; false othervise
    *  since = from now
    *  */

    fun shouldBeWatered (since : Calendar, lastWatereingdate : Calendar) =
            since > lastWatereingdate.apply { add(DAY_OF_YEAR, wateringInterval)  }

    override fun toString() = name
}
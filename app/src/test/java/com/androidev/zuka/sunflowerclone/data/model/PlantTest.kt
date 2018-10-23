package com.androidev.zuka.sunflowerclone.data.model

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import java.util.*
import java.util.Calendar.DAY_OF_YEAR

class PlantTest {

    private var plant = Plant("1","Tomato","A red vegetable",1,2,"")

    @Test
    fun test_default_values() {
        val defaultPlant = Plant("2","Apple","Description",1)
        assertEquals(7,defaultPlant.wateringInterval)
        assertEquals("",defaultPlant.imageUrl)
    }

    @Test
    fun test_shouldBeWatered() {
        Calendar.getInstance().let { now ->
            val lastWateringDate = Calendar.getInstance()


            //test for lastWatering is today
            lastWateringDate.time = now.time
            assertFalse(plant.shouldBeWatered(now,lastWateringDate.apply { add(DAY_OF_YEAR,0) }))

            // Test for lastWateringDate is yesterday.
            lastWateringDate.time = now.time
            assertFalse(plant.shouldBeWatered(now, lastWateringDate.apply { add(DAY_OF_YEAR, -1) }))

            // Test for lastWateringDate is the day before yesterday.
            lastWateringDate.time = now.time
            assertFalse(plant.shouldBeWatered(now, lastWateringDate.apply { add(DAY_OF_YEAR, -2) }))

            // Test for lastWateringDate is some days ago, three days ago, four days ago etc.
            lastWateringDate.time = now.time
            assertTrue(plant.shouldBeWatered(now, lastWateringDate.apply { add(DAY_OF_YEAR, -3) }))
        }
    }

    @Test
    fun test_toString() {
        assertEquals("Tomato",plant.toString())
    }
}
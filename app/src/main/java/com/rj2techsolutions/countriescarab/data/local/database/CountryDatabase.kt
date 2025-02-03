package com.rj2techsolutions.countriescarab.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rj2techsolutions.countriescarab.data.local.dao.CountryDao
import com.rj2techsolutions.countriescarab.data.local.entities.CountryEntity
import com.rj2techsolutions.countriescarab.util.CountryConverters

@Database(entities = [CountryEntity::class], version = 1, exportSchema = false)
@TypeConverters(CountryConverters::class)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}

package com.rj2techsolutions.countriescarab.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rj2techsolutions.countriescarab.data.local.entities.CountryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries: List<CountryEntity>)

    @Query("SELECT * FROM countries WHERE region = :region")
    suspend fun getCountriesByRegion(region: String): Flow<List<CountryEntity>>

    @Query("SELECT * FROM countries WHERE name = :name LIMIT 1")
    suspend fun getCountryByName(name: String): Flow<CountryEntity?>
}

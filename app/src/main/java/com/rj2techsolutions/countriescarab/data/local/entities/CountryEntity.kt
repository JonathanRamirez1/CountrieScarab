package com.rj2techsolutions.countriescarab.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey val name: String,
    val officialName: String?,
    val capital: String?,
    val population: Int?,
    val region: String?,
    val languages: String?,
    val currencyName: String?,
    val currencySymbol: String?,
    val flagUrl: String?
)


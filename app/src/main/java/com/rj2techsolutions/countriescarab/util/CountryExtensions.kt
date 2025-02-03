package com.rj2techsolutions.countriescarab.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rj2techsolutions.countriescarab.data.local.entities.CountryEntity
import com.rj2techsolutions.countriescarab.data.remote.response.CountryResponse
import com.rj2techsolutions.countriescarab.domain.model.Country

fun CountryResponse.toDomain(): Country {
    return Country(
        name = this.name?.common.orEmpty(),
        officialName = this.name?.official.orEmpty(),
        capital = this.capital?.firstOrNull().orEmpty(),
        population = this.population ?: 0,
        region = this.region.orEmpty(),
        languages = this.languages ?: emptyMap(),
        currencyName = this.currencies?.values?.firstOrNull()?.name.orEmpty(),
        currencySymbol = this.currencies?.values?.firstOrNull()?.symbol.orEmpty(),
        flagUrl = this.flags?.png.orEmpty()
    )
}

fun List<CountryResponse>.toDomainListFromResponse(): List<Country> = this.map { it.toDomain() }

fun Country.toEntity(): CountryEntity {
    return CountryEntity(
        name = this.name.orEmpty(),
        officialName = this.officialName,
        capital = this.capital,
        population = this.population,
        region = this.region,
        languages = this.languages?.let { Gson().toJson(it) },
        currencyName = this.currencyName,
        currencySymbol = this.currencySymbol,
        flagUrl = this.flagUrl
    )
}

fun List<Country>.toEntityList(): List<CountryEntity> = this.map { it.toEntity() }

fun CountryEntity.toDomain(): Country {
    return Country(
        name = this.name,
        officialName = this.officialName,
        capital = this.capital,
        population = this.population,
        region = this.region,
        languages = this.languages?.let { Gson().fromJson(it, object : TypeToken<Map<String, String>>() {}.type) },
        currencyName = this.currencyName,
        currencySymbol = this.currencySymbol,
        flagUrl = this.flagUrl
    )
}

fun List<CountryEntity>.toDomainListFromEntity(): List<Country> = this.map { it.toDomain() }
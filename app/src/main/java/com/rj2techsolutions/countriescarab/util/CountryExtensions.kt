package com.rj2techsolutions.countriescarab.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rj2techsolutions.countriescarab.data.local.entities.CountryEntity
import com.rj2techsolutions.countriescarab.data.remote.models.CurrencyModel
import com.rj2techsolutions.countriescarab.data.remote.models.FlagsModel
import com.rj2techsolutions.countriescarab.data.remote.models.NameModel
import com.rj2techsolutions.countriescarab.data.remote.response.CountryResponse
import com.rj2techsolutions.countriescarab.domain.model.Country


fun CountryEntity.toRemote(): CountryResponse {
    return CountryResponse(
        name = NameModel(this.name, this.officialName),
        capital = listOfNotNull(this.capital),
        population = this.population,
        region = this.region,
        languages = this.languages?.let { Gson().fromJson(it, object : TypeToken<Map<String, String>>() {}.type) },
        currencies = this.currencyName?.let { mapOf(it to CurrencyModel(name = it, symbol = this.currencySymbol)) },
        flags = FlagsModel(png = this.flagUrl, svg = null)
    )
}

fun List<CountryEntity>.toRemoteList(): List<CountryResponse> = this.map { it.toRemote() }

fun CountryResponse.toEntity(): CountryEntity {
    return CountryEntity(
        name = this.name?.common ?: "",
        officialName = this.name?.official,
        capital = this.capital?.firstOrNull(),
        population = this.population,
        region = this.region,
        languages = this.languages?.let { Gson().toJson(it) },
        currencyName = this.currencies?.values?.firstOrNull()?.name,
        currencySymbol = this.currencies?.values?.firstOrNull()?.symbol,
        flagUrl = this.flags?.png
    )
}

fun List<CountryResponse>.toEntityList(): List<CountryEntity> = this.map { it.toEntity() }

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


fun List<CountryResponse>.toDomainList(): List<Country> = this.map { it.toDomain() }

fun Country.toEntity(): CountryEntity {
    return CountryEntity(
        name = this.name,
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

fun List<CountryEntity>.toDomainList(): List<Country> = this.map { it.toDomain() }
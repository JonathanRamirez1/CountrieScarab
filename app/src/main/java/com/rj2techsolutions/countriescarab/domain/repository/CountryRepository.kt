package com.rj2techsolutions.countriescarab.domain.repository

import com.rj2techsolutions.countriescarab.domain.model.Country
import com.rj2techsolutions.countriescarab.util.Resource
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    fun getCountriesByRegion(region: String): Flow<Resource<List<Country>>>
    fun getCountryByName(name: String): Flow<Resource<Country>>
}
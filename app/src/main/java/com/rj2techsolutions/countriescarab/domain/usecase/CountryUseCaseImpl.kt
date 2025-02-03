package com.rj2techsolutions.countriescarab.domain.usecase

import com.rj2techsolutions.countriescarab.domain.model.Country
import com.rj2techsolutions.countriescarab.domain.repository.CountryRepository
import com.rj2techsolutions.countriescarab.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountryUseCaseImpl @Inject constructor(
    private val repository: CountryRepository
) : CountryUseCase {

    override fun getCountriesByRegion(region: String): Flow<Resource<List<Country>>> {
        return repository.getCountriesByRegion(region)
    }

    override fun getCountryByName(name: String): Flow<Resource<Country>> {
        return repository.getCountryByName(name)
    }
}

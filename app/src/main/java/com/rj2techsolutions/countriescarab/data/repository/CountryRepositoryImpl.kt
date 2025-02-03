package com.rj2techsolutions.countriescarab.data.repository

import com.rj2techsolutions.countriescarab.data.local.dao.CountryDao
import com.rj2techsolutions.countriescarab.data.remote.network.CountryApi
import com.rj2techsolutions.countriescarab.di.IoDispatcher
import com.rj2techsolutions.countriescarab.domain.model.Country
import com.rj2techsolutions.countriescarab.domain.repository.CountryRepository
import com.rj2techsolutions.countriescarab.util.Resource
import com.rj2techsolutions.countriescarab.util.toDomain
import com.rj2techsolutions.countriescarab.util.toDomainListFromEntity
import com.rj2techsolutions.countriescarab.util.toDomainListFromResponse
import com.rj2techsolutions.countriescarab.util.toEntity
import com.rj2techsolutions.countriescarab.util.toEntityList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val localDataSource: CountryDao,
    private val remoteDataSource: CountryApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): CountryRepository {

   override fun getCountriesByRegion(region: String): Flow<Resource<List<Country>>> = flow {
        emit(Resource.loading())

        val localData = localDataSource.getCountriesByRegion(region).first().toDomainListFromEntity()
        if (localData.isNotEmpty()) {
            emit(Resource.success(localData))
        } else {
            try {
                val remoteData = remoteDataSource.getRegions(region).toDomainListFromResponse()
                localDataSource.insertCountries(remoteData.toEntityList())
                val updatedLocalData = localDataSource.getCountriesByRegion(region).first().toDomainListFromEntity()
                emit(Resource.success(updatedLocalData))
            } catch (e: Exception) {
                emit(Resource.error("Error obteniendo datos remotos: ${e.message}"))
            }
        }
    }.flowOn(ioDispatcher)

    override fun getCountryByName(name: String): Flow<Resource<Country>> = flow {
        emit(Resource.loading())

        val localData = localDataSource.getCountryByName(name).first()?.toDomain()
        if (localData != null) {
            emit(Resource.success(localData))
            return@flow
        }

        try {
            val remoteData = remoteDataSource.getCountries(name).firstOrNull()?.toDomain()
            if (remoteData != null) {
                localDataSource.insertCountries(listOf(remoteData.toEntity()))
                emit(Resource.success(remoteData))
            } else {
                emit(Resource.error("No se encontraron datos en la API."))
            }
        } catch (e: Exception) {
            emit(Resource.error("Error obteniendo datos remotos: ${e.message}"))
        }
    }.flowOn(ioDispatcher)
}


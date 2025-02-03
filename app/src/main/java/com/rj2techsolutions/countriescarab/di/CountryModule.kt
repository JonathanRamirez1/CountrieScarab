package com.rj2techsolutions.countriescarab.di

import com.rj2techsolutions.countriescarab.data.repository.CountryRepositoryImpl
import com.rj2techsolutions.countriescarab.domain.repository.CountryRepository
import com.rj2techsolutions.countriescarab.domain.usecase.CountryUseCase
import com.rj2techsolutions.countriescarab.domain.usecase.CountryUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountryModule {

    @Singleton
    @Provides
    fun provideRepository(countryRepositoryImpl: CountryRepositoryImpl): CountryRepository =
        countryRepositoryImpl

    @Singleton
    @Provides
    fun provideUseCase(countryUseCaseImpl: CountryUseCaseImpl): CountryUseCase =
        countryUseCaseImpl
}
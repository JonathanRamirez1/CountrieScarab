package com.rj2techsolutions.countriescarab.presentation.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rj2techsolutions.countriescarab.domain.model.Country
import com.rj2techsolutions.countriescarab.domain.usecase.CountryUseCase
import com.rj2techsolutions.countriescarab.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val useCase: CountryUseCase
) : ViewModel() {

    private val _countries = MutableStateFlow<Resource<List<Country>>>(Resource.loading())
    val countries: StateFlow<Resource<List<Country>>> = _countries.asStateFlow()

    private val _selectedCountry = MutableStateFlow<Resource<Country>>(Resource.loading())
    val selectedCountry: StateFlow<Resource<Country>> = _selectedCountry.asStateFlow()

    fun fetchCountriesByRegion(region: String) {
        viewModelScope.launch {
            useCase.getCountriesByRegion(region).collect { resource ->
                _countries.value = resource
            }
        }
    }

    fun fetchCountryByName(name: String) {
        viewModelScope.launch {
            useCase.getCountryByName(name).collect { resource ->
                _selectedCountry.value = resource
            }
        }
    }
}

package com.rj2techsolutions.countriescarab.data.remote.network

import com.rj2techsolutions.countriescarab.data.remote.response.CountryResponse
import com.rj2techsolutions.countriescarab.util.Constants.ENDPOINT_NAME
import com.rj2techsolutions.countriescarab.util.Constants.ENDPOINT_REGION
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {

    @GET("${ENDPOINT_REGION}{regionName}")
    suspend fun getRegions(@Path("regionName") regionName: String): List<CountryResponse>

    @GET("${ENDPOINT_NAME}{countryName}")
    suspend fun getCountries(@Path("countryName") countryName: String): List<CountryResponse>
}
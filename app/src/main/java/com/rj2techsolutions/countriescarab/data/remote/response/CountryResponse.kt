package com.rj2techsolutions.countriescarab.data.remote.response

import com.google.gson.annotations.SerializedName
import com.rj2techsolutions.countriescarab.data.remote.models.CurrencyModel
import com.rj2techsolutions.countriescarab.data.remote.models.FlagsModel
import com.rj2techsolutions.countriescarab.data.remote.models.NameModel

data class CountryResponse(
    @SerializedName("name") val name: NameModel? = null,
    @SerializedName("capital") val capital: List<String>? = null,
    @SerializedName("population") val population: Int? = null,
    @SerializedName("region") val region: String? = null,
    @SerializedName("languages") val languages: Map<String, String>? = null,
    @SerializedName("currencies") val currencies: Map<String, CurrencyModel>? = null,
    @SerializedName("flags") val flags: FlagsModel? = null
)

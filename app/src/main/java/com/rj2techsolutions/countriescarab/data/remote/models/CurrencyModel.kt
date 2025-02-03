package com.rj2techsolutions.countriescarab.data.remote.models

import com.google.gson.annotations.SerializedName

data class CurrencyModel(
    @SerializedName("name") val name: String? = null,
    @SerializedName("symbol") val symbol: String? = null
)


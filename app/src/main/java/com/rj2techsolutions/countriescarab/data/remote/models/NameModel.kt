package com.rj2techsolutions.countriescarab.data.remote.models

import com.google.gson.annotations.SerializedName

data class NameModel(
    @SerializedName("common") val common: String? = null,
    @SerializedName("official") val official: String? = null
)
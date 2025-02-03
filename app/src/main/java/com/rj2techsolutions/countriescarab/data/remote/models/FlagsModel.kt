package com.rj2techsolutions.countriescarab.data.remote.models

import com.google.gson.annotations.SerializedName

data class FlagsModel(
    @SerializedName("png") val png: String? = null,
    @SerializedName("svg") val svg: String? = null
)

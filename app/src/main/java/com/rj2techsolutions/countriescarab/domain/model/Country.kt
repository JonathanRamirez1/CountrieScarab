package com.rj2techsolutions.countriescarab.domain.model

data class Country(
    val name: String?,
    val officialName: String?,
    val capital: String?,
    val population: Int?,
    val region: String?,
    val languages: Map<String, String>?,
    val currencyName: String?,
    val currencySymbol: String?,
    val flagUrl: String?
)

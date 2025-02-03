package com.rj2techsolutions.countriescarab.presentation.country.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rj2techsolutions.countriescarab.presentation.country.viewmodel.CountryViewModel
import com.rj2techsolutions.countriescarab.util.Resource
import com.rj2techsolutions.countriescarab.domain.model.Country
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun CountryListScreen(
    countryViewModel: CountryViewModel = hiltViewModel(),
    onCountryClick: (Country) -> Unit,
    onCompareClick: () -> Unit
) {
    val countriesResource by countryViewModel.countries.collectAsState()
    var selectedRegion by remember { mutableStateOf("Americas") }
    var selectedCountries by remember { mutableStateOf(setOf<String>()) }

    LaunchedEffect(Unit) {
        countryViewModel.fetchCountriesByRegion(selectedRegion)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        val regions = listOf("Americas", "Europe", "Africa", "Asia")
        TabRow(
            selectedTabIndex = regions.indexOf(selectedRegion),
            modifier = Modifier.fillMaxWidth()
        ) {
            regions.forEachIndexed { _, region ->
                Tab(
                    selected = region == selectedRegion,
                    onClick = {
                        selectedRegion = region
                        countryViewModel.fetchCountriesByRegion(region)
                    },
                    text = { Text(region) }
                )
            }
        }

        when (countriesResource.status) {
            Resource.Status.LOADING -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            Resource.Status.SUCCESS -> {
                CountryListScreenContent(
                    countries = countriesResource.data ?: emptyList(),
                    selectedCountries = selectedCountries,
                    onCountryClick = onCountryClick,
                    onCountryChecked = { country, isChecked ->
                        selectedCountries = if (isChecked) {
                            selectedCountries + (country.name ?: "")
                        } else {
                            selectedCountries - (country.name ?: "")
                        }
                    },
                    onCompareClick = {
                        val selected = countriesResource.data?.filter { country ->
                            selectedCountries.any { it.equals(country.name, ignoreCase = true) }
                        } ?: emptyList()

                        if (selected.isNotEmpty()) {
                            countryViewModel.setSelectedCountries(selected)
                            onCompareClick()
                        } else {
                            Log.e("CountryListScreen", "No se seleccionaron países")
                        }
                    }
                )
            }
            Resource.Status.ERROR -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error: ${countriesResource.message}")
                }
            }
        }
    }
}

@Composable
fun CountryListScreenContent(
    countries: List<Country>,
    selectedCountries: Set<String>,
    onCountryClick: (Country) -> Unit,
    onCountryChecked: (Country, Boolean) -> Unit,
    onCompareClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (countries.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No hay países disponibles")
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(countries) { country ->
                    CountryListItem(
                        country = country,
                        isSelected = selectedCountries.contains(country.name),
                        onClick = { onCountryClick(country) },
                        onCheckedChange = { isChecked -> onCountryChecked(country, isChecked) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onCompareClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = selectedCountries.isNotEmpty()
        ) {
            Text("Comparar Países Seleccionados")
        }
    }
}

@Composable
fun CountryListItem(
    country: Country,
    isSelected: Boolean,
    onClick: () -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = country.flagUrl,
                contentDescription = "Bandera de ${country.name}",
                modifier = Modifier.size(40.dp).clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = country.name.orEmpty(),
                modifier = Modifier.weight(1f),
                fontSize = 18.sp
            )

            Checkbox(
                checked = isSelected,
                onCheckedChange = onCheckedChange
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountryListScreenPreview() {
    val fakeCountries = listOf(
        Country(
            name = "Argentina",
            officialName = "República Argentina",
            capital = "Buenos Aires",
            population = 45195777,
            region = "Americas",
            languages = mapOf("spa" to "Spanish"),
            currencyName = "Peso Argentino",
            currencySymbol = "$",
            flagUrl = "https://flagcdn.com/w320/ar.png"
        ),
        Country(
            name = "Colombia",
            officialName = "República de Colombia",
            capital = "Bogotá",
            population = 50882884,
            region = "Americas",
            languages = mapOf("spa" to "Spanish"),
            currencyName = "Peso Colombiano",
            currencySymbol = "$",
            flagUrl = "https://flagcdn.com/w320/co.png"
        )
    )

    val fakeSelectedCountries = setOf("Argentina")

    CountryListScreenContent(
        countries = fakeCountries,
        selectedCountries = fakeSelectedCountries,
        onCountryClick = {},
        onCountryChecked = { _, _ -> },
        onCompareClick = {}
    )
}

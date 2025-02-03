package com.rj2techsolutions.countriescarab.presentation.country.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rj2techsolutions.countriescarab.domain.model.Country

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDetailScreen(
    country: Country,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = country.flagUrl,
                contentDescription = "Bandera de ${country.name}",
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Nombre oficial: ${country.officialName.orEmpty()}")
            Text("Capital: ${country.capital.orEmpty()}")
            Text("Población: ${country.population ?: 0}")
            Text("Idiomas: ${country.languages?.values?.joinToString().orEmpty()}")
            Text("Moneda: ${country.currencyName.orEmpty()} (${country.currencySymbol.orEmpty()})")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountryDetailScreenPreview() {
    val country = Country(
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
    CountryDetailScreen(country = country, onBackClick = {})
}

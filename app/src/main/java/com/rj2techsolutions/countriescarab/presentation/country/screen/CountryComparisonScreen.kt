package com.rj2techsolutions.countriescarab.presentation.country.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rj2techsolutions.countriescarab.domain.model.Country

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryComparisonScreen(
    selectedCountries: List<Country>,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Comparación de Población") },
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
                .padding(16.dp)
        ) {
            Text(
                text = "Comparación de Población",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (selectedCountries.isEmpty()) {
                Text(
                    text = "No hay países seleccionados para comparar.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 32.dp)
                )
            } else {
                BarChart(
                    countries = selectedCountries,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun BarChart(
    countries: List<Country>,
    modifier: Modifier = Modifier
) {
    val maxPopulation = countries.maxOfOrNull { it.population ?: 0 }?.toFloat() ?: 1f

    Canvas(modifier = modifier) {
        val barWidth = size.width / (countries.size * 2)
        val maxHeight = size.height
        val spacing = barWidth

        countries.forEachIndexed { index, country ->
            val population = country.population?.toFloat() ?: 0f
            val barHeight = (population / maxPopulation) * maxHeight

            // Definir colores alternados para las barras
            val color = when (index % 3) {
                0 -> Color(0xFF2196F3) // Azul
                1 -> Color(0xFFFF5722) // Rojo
                else -> Color(0xFF4CAF50) // Verde
            }

            // Dibujar barra
            drawRect(
                color = color,
                topLeft = Offset(
                    x = index * (barWidth + spacing),
                    y = maxHeight - barHeight
                ),
                size = Size(
                    width = barWidth,
                    height = barHeight
                )
            )

            // Dibujar etiqueta debajo de la barra
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    country.name.orEmpty(),
                    index * (barWidth + spacing) + barWidth / 2,
                    maxHeight + 20f, // Desplazamiento ligeramente debajo de las barras
                    android.graphics.Paint().apply {
                        textSize = 32f
                        textAlign = android.graphics.Paint.Align.CENTER
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CountryComparisonScreenPreview() {
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
    CountryComparisonScreen(selectedCountries = fakeCountries, onBackClick = {})
}

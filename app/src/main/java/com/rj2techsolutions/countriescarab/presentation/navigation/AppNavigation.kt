package com.rj2techsolutions.countriescarab.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rj2techsolutions.countriescarab.presentation.country.screen.CountryComparisonScreen
import com.rj2techsolutions.countriescarab.presentation.country.screen.CountryDetailScreen
import com.rj2techsolutions.countriescarab.presentation.country.screen.CountryListScreen
import com.rj2techsolutions.countriescarab.presentation.country.viewmodel.CountryViewModel
import com.rj2techsolutions.countriescarab.util.Resource

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val countryViewModel: CountryViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "country_list"
    ) {
        composable("country_list") {
            CountryListScreen(
                countryViewModel = countryViewModel,
                onCountryClick = { country ->
                    navController.navigate("country_detail/${country.name}")
                },
                onCompareClick = {
                    navController.navigate("country_comparison")
                }
            )
        }
        composable(
            route = "country_detail/{countryName}",
            arguments = listOf(navArgument("countryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val countryName = backStackEntry.arguments?.getString("countryName") ?: ""
            val countryViewModel: CountryViewModel = hiltViewModel()

            LaunchedEffect(countryName) {
                countryViewModel.fetchCountryByName(countryName)
            }

            val countryResource by countryViewModel.selectedCountry.collectAsState()

            when (countryResource.status) {
                Resource.Status.LOADING -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                Resource.Status.SUCCESS -> {
                    countryResource.data?.let { country ->
                        CountryDetailScreen(
                            country = country,
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                }
                Resource.Status.ERROR -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Error: ${countryResource.message}")
                    }
                }
            }
        }

        composable("country_comparison") {
            val selectedCountries by countryViewModel.selectedCountries.collectAsState()

            CountryComparisonScreen(
                selectedCountries = selectedCountries,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

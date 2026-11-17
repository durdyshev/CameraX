package com.justme.camerax.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.justme.camerax.presentation.AddUpdateItem
import com.justme.camerax.presentation.CustomCamera
import com.justme.camerax.presentation.ListScreen
import com.justme.camerax.presentation.StartupWizard
import com.justme.camerax.viewmodel.SaleViewModel

@Composable
fun Navigation(context: Context) {
    val navController = rememberNavController()
    val saleViewModel = hiltViewModel<SaleViewModel>()
    val sales by saleViewModel.salesItemList.collectAsState()

    LaunchedEffect(Unit) {
        saleViewModel.getAllSales()
    }

    NavHost(
        navController = navController,
        startDestination = Screen.StartupWizard.route
    ) {
        composable(route = Screen.ListScreen.route) {
            ListScreen(navController)
        }
        composable(route = Screen.StartupWizard.route) {
            StartupWizard(navController)
        }
        composable(route = Screen.AddUpdateItem.route + "/{id}",
            arguments = arrayListOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            AddUpdateItem(navController, id = it.arguments?.getInt("id"))
        }
        composable(route = Screen.CustomCamera.route) {
            CustomCamera(navController)
        }
    }

    LaunchedEffect(sales) {
        if (sales.isNotEmpty()) {
            navController.navigate(Screen.ListScreen.route) {
                popUpTo(Screen.StartupWizard.route) { inclusive = true }
            }
        }
    }
}
package com.justme.camerax.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.justme.camerax.data.local.model.SaleData
import com.justme.camerax.navigation.Screen
import com.justme.camerax.viewmodel.SaleViewModel

@Composable
fun ListScreen(navController: NavHostController) {
    val saleViewModel = hiltViewModel<SaleViewModel>()
    saleViewModel.getAllSales()
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            Text(
                text = "Your Items",
                fontSize = 27.sp,
                modifier = Modifier
                    .fillMaxWidth(1.0F)
                    .height(50.dp)
                    .background(Color(0xFF042956)),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            LazyColumnList(sale = saleViewModel.salesItemList.collectAsState().value)
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddUpdateItem.route + "/0")
                },
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        }
    }
}

@Composable
fun LazyColumnList(sale: List<SaleData>) {
    LazyColumn {
        sale.forEach {
            item { SaleItem(it) }
        }
    }
}
package com.justme.camerax.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.justme.camerax.navigation.Screen

@Composable
fun StartupWizard(navController: NavHostController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize(1.0F)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(1.0F)
                    .fillMaxHeight(0.5F),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "CameraX",
                    fontWeight = FontWeight.W600,
                    fontStyle = FontStyle.Italic,
                    fontSize = 24.sp,
                    color = Color.Yellow,
                    letterSpacing = 3.sp
                )
                Text(
                    text = "Jetpack Compose",
                    fontWeight = FontWeight.W600,
                    fontStyle = FontStyle.Italic,
                    fontSize = 24.sp,
                    color = Color.Yellow,
                    letterSpacing = 3.sp
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(1.0F)
                    .fillMaxHeight(1.0F)
                    .padding(bottom = 24.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Button(onClick = { navController.navigate(Screen.ListScreen.route) }) {
                    Text(text = "START")
                }
            }

        }


    }
}
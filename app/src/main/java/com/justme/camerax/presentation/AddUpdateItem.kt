package com.justme.camerax.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.justme.camerax.R
import com.justme.camerax.data.local.model.SaleData
import com.justme.camerax.navigation.Screen
import com.justme.camerax.viewmodel.SaleViewModel

class BitmapClass {
    companion object {
        var bitmap: Bitmap? = null
        var detectedDescription: String? = null
    }
}

@Composable
fun AddUpdateItem(navController: NavHostController, id: Int?) {
    val saleViewModel = hiltViewModel<SaleViewModel>()
    var description by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(BitmapClass.detectedDescription) {
        val detectedDescription = BitmapClass.detectedDescription
        if (!detectedDescription.isNullOrBlank() && description.isBlank()) {
            description = detectedDescription
        }
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission Accepted: Do something
            Log.d("ExampleScreen", "PERMISSION GRANTED")
            navController.navigate(Screen.CustomCamera.route)

        } else {
            // Permission Denied: Do something
            Log.d("ExampleScreen", "PERMISSION DENIED")
        }
    }
    Scaffold(topBar = {
        Text(
            text = "New Item",
            fontSize = 27.sp,
            modifier = Modifier
                .fillMaxWidth(1.0F)
                .height(60.dp)
                .background(Color(0xFF042956)),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }) { it ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                CustomTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = "Description"
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    modifier = Modifier
                        .fillMaxWidth(1.0F)
                        .padding(24.dp)
                        .clickable {
                            when (PackageManager.PERMISSION_GRANTED) {
                                ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.CAMERA
                                ) -> {
                                    navController.navigate(Screen.CustomCamera.route)
                                }

                                else -> {
                                    launcher.launch(Manifest.permission.CAMERA)
                                }
                            }
                        },
                    alignment = Alignment.Center,
                    painter = if (BitmapClass.bitmap != null) {
                        rememberAsyncImagePainter(BitmapClass.bitmap)
                    } else {
                        painterResource(R.drawable.camera_icon)
                    },
                    contentDescription = "background_image",
                )

            }
            Button(modifier = Modifier.align(Alignment.BottomCenter), onClick = {
                saleViewModel.insertSale(
                    SaleData(
                        id = null,
                        desc =description,
                        image = saleViewModel.bitmapToByteArray(BitmapClass.bitmap)
                    )
                )
                navController.navigate(Screen.ListScreen.route)
                BitmapClass.bitmap = null
                BitmapClass.detectedDescription = null
            }) {
                Text(text = "SAVE")
            }
        }
    }
}

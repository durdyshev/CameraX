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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.justme.camerax.data.local.model.SaleData
import com.justme.camerax.navigation.Screen
import com.justme.camerax.viewmodel.SaleViewModel
import com.justme.camerax.R

class BitmapClass {
    companion object {
        var bitmap: Bitmap? = null
    }
}

@Composable
fun AddUpdateItem(navController: NavHostController, id: Int?) {
    val saleViewModel = hiltViewModel<SaleViewModel>()
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

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
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            Text(
                text = "New Item",
                fontSize = 27.sp,
                modifier = Modifier
                    .fillMaxWidth(1.0F)
                    .height(40.dp)
                    .background(Color(0xFF042956)),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Column {
                Column(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .fillMaxHeight(0.5F)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text(text = "Title") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text(text = "Description") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = price,
                        onValueChange = { price = it },
                        label = { Text(text = "Price") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                            }
                        ),
                        modifier = Modifier.fillMaxWidth()
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
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Button(onClick = {
                        saleViewModel.insertSale(
                            SaleData(
                                id = null,
                                title,
                                description,
                                price.toDouble(),
                                saleViewModel.bitmapToByteArray(BitmapClass.bitmap)
                            )
                        )
                        navController.navigate(Screen.ListScreen.route)
                        BitmapClass.bitmap = null
                    }) {
                        Text(text = "SAVE")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

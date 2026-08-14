package com.justme.camerax.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justme.camerax.data.local.model.SaleData
import com.justme.camerax.data.repository.RepositoryInterface
import com.justme.camerax.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class SaleViewModel @Inject constructor(
    private val repositoryInterface: RepositoryInterface
) : ViewModel() {
    private val sales = MutableStateFlow(emptyList<SaleData>())
    val salesItemList = sales.asStateFlow()


    fun getAllSales() {
        viewModelScope.launch(IO) {
            repositoryInterface.getAllSales().collectLatest {
                sales.tryEmit(it)
            }
        }
    }

    fun updateSale(saleData: SaleData) {
        viewModelScope.launch(IO) {
            repositoryInterface.update(saleData)
        }
    }

    fun insertSale(saleData: SaleData) {
        viewModelScope.launch(IO) {
            repositoryInterface.insert(saleData)
        }
    }

    fun bitmapToByteArray(bitmap: Bitmap?): ByteArray? {
        return if (bitmap != null && !bitmap.isRecycled) {
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 10, stream)
            val byteArray: ByteArray = stream.toByteArray()
            //bitmap.recycle()
            byteArray
        } else {
            null
        }

    }
    fun startingFragment(): String {
        return if (sales.value.isEmpty()) {
            Screen.StartupWizard.route
        } else {
            Screen.ListScreen.route
        }
    }
}
package com.justme.camerax.data.repository

import com.justme.camerax.data.local.dao.SaleDao
import com.justme.camerax.data.local.model.SaleData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RepositoryInterface {

    suspend fun insert(saleData: SaleData)

    suspend fun update(saleData: SaleData)

    suspend fun getAllSales(): Flow<List<SaleData>>
}

class Repository @Inject constructor(
    private val dao: SaleDao,
) : RepositoryInterface {
    override suspend fun insert(saleData: SaleData) {
        withContext(IO) {
            dao.insert(saleData)
        }
    }

    override suspend fun update(saleData: SaleData) {
        withContext(IO) {
            dao.update(saleData)
        }
    }

    override suspend fun getAllSales(): Flow<List<SaleData>> {
        return withContext(IO) {
            dao.getAllStudents()
        }
    }

}
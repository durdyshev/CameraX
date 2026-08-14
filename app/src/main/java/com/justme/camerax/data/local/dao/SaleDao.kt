package com.justme.camerax.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.justme.camerax.data.local.model.SaleData
import kotlinx.coroutines.flow.Flow

@Dao
interface SaleDao {

    @Insert
    suspend fun insert(sale: SaleData)

    @Update
    suspend fun update(sale: SaleData)

    @Query("SELECT * FROM sales")
    fun getAllStudents(): Flow<List<SaleData>>
}
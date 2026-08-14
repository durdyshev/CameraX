package com.justme.camerax.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.justme.camerax.data.local.dao.SaleDao
import com.justme.camerax.data.local.model.SaleData

@Database(
    entities = [SaleData::class],
    version = 1,
    exportSchema = false
)
abstract class SaleDatabase : RoomDatabase() {
    abstract val dao: SaleDao
}
package com.justme.camerax.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = tableName)
class SaleData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,
    @ColumnInfo(name = "title")
    var title: String = "",
    @ColumnInfo(name = "desc")
    var desc: String = "",
    @ColumnInfo(name = "price")
    var price: Double = 0.0,
    @ColumnInfo(name = "image")
    val image: ByteArray? = null,
)
const val tableName = "sales"
package com.app.trackingmoney.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class Category(
    @PrimaryKey val id: Int,
    val nameCategory: String
)

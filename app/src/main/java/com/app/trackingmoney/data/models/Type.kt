package com.app.trackingmoney.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "type_table")
data class Type(
    @PrimaryKey val id: Int,
    val nameType: String
)

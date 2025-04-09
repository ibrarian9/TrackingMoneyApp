package com.app.trackingmoney.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "transaction_table")
@Parcelize
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val amount: Double,
    val date: LocalDateTime,
    val categoryId: Int,
    val note: String?,
    val typeId: Int
) : Parcelable {
    val createDateFormatted : String
        get() = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
}

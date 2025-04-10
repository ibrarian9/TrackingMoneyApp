package com.app.trackingmoney.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class TransactionWithDetails(
    @Embedded val transaction: Transaction,

    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: Category,

    @Relation(
        parentColumn = "typeId",
        entityColumn = "id"
    )
    val type: Type
)
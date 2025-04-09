package com.app.trackingmoney.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.trackingmoney.data.models.Transaction

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transaction_table")
    suspend fun getAllTransactions(): List<Transaction>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: Transaction)
}
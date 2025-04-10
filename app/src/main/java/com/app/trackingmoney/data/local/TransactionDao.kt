package com.app.trackingmoney.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.trackingmoney.data.models.BalanceDetails
import com.app.trackingmoney.data.models.Transaction
import com.app.trackingmoney.data.models.TransactionWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transaction_table")
    fun getAllTransactions(): Flow<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: Transaction)

    @androidx.room.Transaction
    @Query("SELECT * FROM transaction_table WHERE typeId = 1")
    suspend fun getAllIncomeTransactionWithDetails(): List<TransactionWithDetails>

    @androidx.room.Transaction
    @Query("SELECT * FROM transaction_table WHERE typeId = 2")
    suspend fun getAllExpenseTransactionWithDetails(): List<TransactionWithDetails>

    @Query("""
        SELECT 
            (SELECT COALESCE(SUM(amount), 0) FROM transaction_table WHERE typeId = 1) AS totalIncome,
            (SELECT COALESCE(SUM(amount), 0) FROM transaction_table WHERE typeId = 2) AS totalExpense,
            ((SELECT COALESCE(SUM(amount), 0) FROM transaction_table WHERE typeId = 1) - 
             (SELECT COALESCE(SUM(amount), 0) FROM transaction_table WHERE typeId = 2)) AS balance
    """)
    fun getBalanceDetails(): Flow<BalanceDetails>
}
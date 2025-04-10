package com.app.trackingmoney.data

import com.app.trackingmoney.data.local.CategoryDao
import com.app.trackingmoney.data.local.TransactionDao
import com.app.trackingmoney.data.local.TypeDao
import com.app.trackingmoney.data.models.BalanceDetails
import com.app.trackingmoney.data.models.Category
import com.app.trackingmoney.data.models.Transaction
import com.app.trackingmoney.data.models.TransactionWithDetails
import com.app.trackingmoney.data.models.Type
import kotlinx.coroutines.flow.Flow

class MainRepository(
    val transactionDao: TransactionDao,
    val typeDao: TypeDao,
    val categoryDao: CategoryDao
) {
    // Expose the Flow of transactions.
    fun getAllTransactionsFlow(): Flow<List<Transaction>> = transactionDao.getAllTransactions()

    suspend fun getAllIncomeTransaction(): List<TransactionWithDetails> {
        return transactionDao.getAllIncomeTransactionWithDetails()
    }

    suspend fun getAllExpenseTransaction(): List<TransactionWithDetails> {
        return transactionDao.getAllExpenseTransactionWithDetails()
    }

    suspend fun insertAllTransaction(data: Transaction) {
        return transactionDao.insert(data)
    }

    suspend fun getAllTypes(): List<Type> {
        return typeDao.getAllTypes()
    }

    suspend fun getAllCategory(): List<Category> {
        return categoryDao.getAllCategories()
    }

    fun getBalanceDetails(): Flow<BalanceDetails> {
        return transactionDao.getBalanceDetails()
    }
}
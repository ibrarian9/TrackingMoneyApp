package com.app.trackingmoney.data

import com.app.trackingmoney.data.local.CategoryDao
import com.app.trackingmoney.data.local.TransactionDao
import com.app.trackingmoney.data.local.TypeDao
import com.app.trackingmoney.data.models.Category
import com.app.trackingmoney.data.models.Transaction
import com.app.trackingmoney.data.models.Type

class MainRepository(
    val transactionDao: TransactionDao,
    val typeDao: TypeDao,
    val categoryDao: CategoryDao
) {
    suspend fun getAllTransaction(): List<Transaction> {
        return transactionDao.getAllTransactions()
    }

    suspend fun getAllTypes(): List<Type> {
        return typeDao.getAllTypes()
    }

    suspend fun getAllCategory(): List<Category> {
        return categoryDao.getAllCategories()
    }
}
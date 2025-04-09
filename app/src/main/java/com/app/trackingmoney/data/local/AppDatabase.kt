package com.app.trackingmoney.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.app.trackingmoney.data.models.Category
import com.app.trackingmoney.data.models.Transaction
import com.app.trackingmoney.data.models.Type
import com.app.trackingmoney.helper.Converters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Transaction::class, Category::class, Type::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao
    abstract fun typeDao(): TypeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    prepopulateDatabase(database.categoryDao(), database.typeDao())
                }
            }
        }

        suspend fun prepopulateDatabase(categoryDao: CategoryDao, typeDao: TypeDao) {
            // Define your default categories and types.
            val defaultCategories = listOf(
                Category(id = 1, nameCategory = "Food"),
                Category(id = 2, nameCategory = "Transport"),
                Category(id = 3, nameCategory = "Utilities")
            )
            val defaultTypes = listOf(
                Type(id = 1, nameType = "Income"),
                Type(id = 2, nameType = "Expense")
            )
            // Insert the data.
            categoryDao.insertAll(defaultCategories)
            typeDao.insertAll(defaultTypes)
        }
    }
}
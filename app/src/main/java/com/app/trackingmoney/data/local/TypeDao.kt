package com.app.trackingmoney.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.trackingmoney.data.models.Type

@Dao
interface TypeDao {
    @Query("SELECT * FROM type_table")
    suspend fun getAllTypes(): List<Type>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(types: List<Type>)
}
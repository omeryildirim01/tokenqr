package com.yildirimomer.tokenqr.data.local.dao

import androidx.room.*
import com.yildirimomer.tokenqr.model.entity.PaymentRecord
import kotlinx.coroutines.flow.Flow

/**
 * Created by OMER YILDIRIM on 12/5/21.
 * yildirimomer01@gmail.com
 */

@Dao
interface PaymentDao {
    @Query("SELECT count(rowid) FROM payments")
    suspend fun numberOfPaymentsInDB() : Int

    @Query("SELECT * FROM payments ORDER BY created_date ASC")
    fun getAll(): Flow<List<PaymentRecord>>

    @Insert
    suspend fun insert(paymentRecord : PaymentRecord)

    @Update
    suspend fun update(paymentRecord: PaymentRecord)

    @Delete
    suspend fun delete(paymentRecord: PaymentRecord)

    @Query("DELETE FROM payments")
    fun deleteAll()
}
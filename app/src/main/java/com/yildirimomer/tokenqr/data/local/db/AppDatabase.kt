package com.yildirimomer.tokenqr.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yildirimomer.tokenqr.core.Constants.Companion.DATABASE_NAME
import com.yildirimomer.tokenqr.data.local.dao.PaymentDao
import com.yildirimomer.tokenqr.model.entity.PaymentRecord
import com.yildirimomer.tokenqr.util.Converters

/**
 * Created by OMER YILDIRIM on 12/5/21.
 * yildirimomer01@gmail.com
 */
@Database(entities = [PaymentRecord::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun paymentDao(): PaymentDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}

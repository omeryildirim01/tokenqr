package com.yildirimomer.tokenqr.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by OMER YILDIRIM on 12/5/21.
 * yildirimomer01@gmail.com
 */
@Entity(tableName = "payments")
data class PaymentRecord(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "rowid")
    val id: Int  = 0,
    @ColumnInfo(name = "application_id")
    val applicationID: String?,
    @ColumnInfo(name = "pos_id")
    val posID: String?,
    @ColumnInfo(name = "return_code")
    val returnCode: Int?,
    @ColumnInfo(name = "return_desc")
    val returnDesc: String?,
    @ColumnInfo(name = "session_id")
    val sessionID: String?,
    @ColumnInfo(name = "payment_amount")
    val paymentAmount: Int?,
    @ColumnInfo(name = "created_date", defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: Long? = System.currentTimeMillis()
)